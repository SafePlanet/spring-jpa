/* Copyright (C) SafePlanet Innovations, LLP - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by SafePlanet Innovations <spinnovations.india@gmail.com>, October 2015
 */
package com.spi.uvschool.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.spi.uvschool.api.ServiceResponse;
import com.spi.uvschool.config.ApplicationConfig;
import com.spi.uvschool.dao.UserDAO;
import com.spi.uvschool.domain.Address;
import com.spi.uvschool.domain.AuthorizationToken;
import com.spi.uvschool.domain.Role;
import com.spi.uvschool.domain.User;
import com.spi.uvschool.exception.AuthenticationException;
import com.spi.uvschool.exception.AuthorizationException;
import com.spi.uvschool.exception.DuplicateUserException;
import com.spi.uvschool.exception.UserNotFoundException;
import com.spi.uvschool.gateway.EmailServicesGateway;
import com.spi.uvschool.service.BaseService;
import com.spi.uvschool.service.CommonService;
import com.spi.uvschool.service.UserService;
import com.spi.uvschool.user.api.AuthenticatedUserToken;
import com.spi.uvschool.user.api.ChangePasswordRequest;
import com.spi.uvschool.user.api.CreateUserRequest;
import com.spi.uvschool.user.api.ExternalUser;
import com.spi.uvschool.user.api.LoginRequest;
import com.spi.uvschool.user.api.UpdateUserRequest;
import com.spi.uvschool.user.social.JpaUsersConnectionRepository;
import com.spi.uvschool.util.StringUtil;

/**
 * Service for managing User accounts
 *
 * @author:
 */
@Service("userService")
@Transactional
public class UserServiceImpl extends BaseService implements UserService {

	private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	ApplicationConfig config;
	/**
	 * For Social API handling
	 */
	private UsersConnectionRepository jpaUsersConnectionRepository;

	private UserDAO userRepository;

	private ApplicationConfig applicationConfig;

	@Autowired
	protected EmailServicesGateway emailServicesGateway;

	@Autowired
	protected CommonService commonService;


	public UserServiceImpl(Validator validator) {
		super(validator);
	}

	@Autowired
	public UserServiceImpl(UsersConnectionRepository usersConnectionRepository, Validator validator, ApplicationConfig applicationConfig) {
		this(validator);
		this.jpaUsersConnectionRepository = usersConnectionRepository;
		((JpaUsersConnectionRepository) this.jpaUsersConnectionRepository).setUserService(this);
		this.applicationConfig = applicationConfig;
	}

	/**
	 * {@inheritDoc}
	 *
	 * This method creates a User with the given Role. A check is made to see if
	 * the username already exists and a duplication check is made on the email
	 * address if it is present in the request.
	 * <P>
	 * </P>
	 * The password is hashed and a AuthorizationToken generated for subsequent
	 * authorization of role-protected requests.
	 *
	 */

	@Transactional
	public AuthenticatedUserToken createUser(CreateUserRequest request, Role role) {
		validate(request);
		User searchedForUser = userRepository.findByEmailAddress(request.getUser().getEmailAddress());
		if (searchedForUser != null) {
			throw new DuplicateUserException();
		}

		User newUser = createNewUser(request, role);
		AuthenticatedUserToken token = new AuthenticatedUserToken(newUser.getUuid().toString(), createAuthorizationToken(newUser).getToken());
		userRepository.save(newUser);
		return token;
	}

	@Transactional
	public AuthenticatedUserToken createUser(Role role) {
		User user = new User();
		user.setRole(role);
		AuthenticatedUserToken token = new AuthenticatedUserToken(user.getUuid().toString(), createAuthorizationToken(user).getToken());
		userRepository.save(user);
		return token;
	}

	@Transactional
	public Role getUserRole(String userUUID) {
		User user = ensureUserIsLoaded(userUUID);
		if (user != null) {
			return user.getRole();
		} else {
			return Role.anonymous;
		}
	}

	/**
	 * {@inheritDoc}
	 *
	 * Login supports authentication against an email attribute. If a User is
	 * retrieved that matches, the password in the request is hashed and
	 * compared to the persisted password for the User account.
	 */
	@Transactional
	public AuthenticatedUserToken login(LoginRequest request) {
		validate(request);
		AuthenticatedUserToken token=new AuthenticatedUserToken();
		LOG.debug(request.getUsername() + " user trying to access the system.");
		User user = null;
		user = userRepository.findByEmailAddress(request.getUsername());
		
		if (user == null || user.getIsEnable() == 0) {
			token.setMessage("User not found");
		}else{
		String hashedPassword = null;
		try {
			hashedPassword = user.hashPassword(request.getPassword());
		} catch (Exception e) {
			token.setMessage("User not found");
		}
		token= new AuthenticatedUserToken(user.getUuid().toString(), createAuthorizationToken(user).getToken(),String.valueOf(100));
		
	}return token;
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * Associate a Connection with a User account. If one does not exist a new
	 * User is created and linked to the {@link com.spi.domain.SocialUser}
	 * represented in the Connection details.
	 *
	 * <P>
	 * </P>
	 *
	 * A AuthorizationToken is generated and any Profile data that can be
	 * collected from the Social account is propagated to the User object.
	 *
	 */
	@Transactional
	public AuthenticatedUserToken socialLogin(Connection<?> connection) {

		List<String> userUuids = jpaUsersConnectionRepository.findUserIdsWithConnection(connection);
		if (userUuids.size() == 0) {
			throw new AuthenticationException();
		}
		User user = userRepository.findByUuid(userUuids.get(0)); // take the
		// first one
		// if there
		// are
		// multiple
		// userIds
		// for this
		// provider
		// Connection
		if (user == null || user.getIsEnable() == 0) {
			throw new AuthenticationException();
		}
		updateUserFromProfile(connection, user);
		return new AuthenticatedUserToken(user.getUuid().toString(), createAuthorizationToken(user).getToken());
	}

	public ExternalUser getUser(ExternalUser requestingUser, String userIdentifier) {
		Assert.notNull(requestingUser);
		Assert.notNull(userIdentifier);
		User user = ensureUserIsLoaded(userIdentifier);
		return new ExternalUser(user);
	}

	public void deleteUser(ExternalUser userMakingRequest, String userId) {
		Assert.notNull(userMakingRequest);
		Assert.notNull(userId);
		User userToDelete = ensureUserIsLoaded(userId);
		if (userMakingRequest.getRole().equalsIgnoreCase(Role.administrator.toString())
				&& (userToDelete.hasRole(Role.anonymous) || userToDelete.hasRole(Role.authenticated))) {
			userRepository.delete(userToDelete);
		} else {
			throw new AuthorizationException("User cannot be deleted. Only users with anonymous or authenticated role can be deleted.");
		}
	}

	@Transactional
	public ExternalUser saveUser(String userId, UpdateUserRequest request) {
		validate(request);
		User user = ensureUserIsLoaded(userId);
		if (request.getFirstName() != null) {
			user.setFirstName(request.getFirstName());
		}
		if (request.getLastName() != null) {
			user.setLastName(request.getLastName());
		}
		if (request.getEmailAddress() != null) {
			if (!request.getEmailAddress().equals(user.getEmailAddress())) {
				user.setEmailAddress(request.getEmailAddress());
				user.setVerified(false);
			}
		}
		userRepository.save(user);
		return new ExternalUser(user);
	}

	@Transactional
	public ExternalUser updateUser(String userId, UpdateUserRequest request) {
		validate(request);
		// User user = new User(request);
		User existingUser = ensureUserIsLoaded(userId);

		if (request.getEmailAddress() != null) {
			if (!request.getEmailAddress().equals(existingUser.getEmailAddress())) {
				existingUser.setEmailAddress(request.getEmailAddress());
				existingUser.setVerified(false);
			}
		}
		existingUser.setUserDetails(request);
		userRepository.save(existingUser);
		return new ExternalUser(existingUser);
	}

	@Override
	public AuthorizationToken createAuthorizationToken(User user) {
		if (user.getAuthorizationToken() == null) {
			user.setAuthorizationToken(new AuthorizationToken(user, applicationConfig.getAuthorizationExpiryTimeInSeconds()));
		} else if (user.getAuthorizationToken() != null && user.getAuthorizationToken().hasExpired()) {
			// user.setAuthorizationToken(new AuthorizationToken(user,
			// applicationConfig.getAuthorizationExpiryTimeInSeconds()));
			user.getAuthorizationToken()
					.setExpirationDate(new Date(System.currentTimeMillis() + (applicationConfig.getAuthorizationExpiryTimeInSeconds() * 1000L)));
		}
		userRepository.save(user);

		return user.getAuthorizationToken();
	}

	private User createNewUser(CreateUserRequest request, Role role) {
		User userToSave = new User(request.getUser());
		Address addressToSave = new Address(request.getAddress(), userToSave);
		try {
			ArrayList<Address> addresses = new ArrayList<Address>();
			addresses.add(addressToSave);
			userToSave.setAddresses(addresses);
			userToSave.setHashedPassword(userToSave.hashPassword(request.getPassword().getPassword()));
		} catch (Exception e) {
			throw new AuthenticationException();
		}
		userToSave.setRole(role);
		userToSave.setUsageConsent('1');
		userToSave.setIsEnable(1);
		return userToSave;
	}

	private void updateUserFromProfile(Connection<?> connection, User user) {
		UserProfile profile = connection.fetchUserProfile();
		user.setEmailAddress(profile.getEmail());
		user.setFirstName(profile.getFirstName());
		user.setLastName(profile.getLastName());
		// users logging in from social network are already verified
		user.setVerified(true);
		if (user.hasRole(Role.anonymous)) {
			user.setRole(Role.authenticated);
		}
		userRepository.save(user);
	}

	private User ensureUserIsLoaded(String userIdentifier) {
		User user = null;
		if (StringUtil.isValidUuid(userIdentifier)) {
			user = userRepository.findByUuid(userIdentifier);
		} else {
			user = userRepository.findByEmailAddress(userIdentifier);
		}
		if (user == null) {
			throw new UserNotFoundException();
		}
		return user;
	}

	public void deactivateUser(String userUUID, String action) {
		User user = userRepository.findByUuid(userUUID);
		if (user != null) {
			if ("enable".equals(action)) {
				user.setIsEnable(1);
			} else if ("disable".equals(action)) {
				user.setIsEnable(0);
			}
		}
		userRepository.save(user);
	}
	@Override
	public void saveFCMToken(String fcmToken, String userId) {
		ServiceResponse response = new ServiceResponse();
		try {

			String Id = userId.replaceAll("\\s", "");

			User user = userRepository.findByUuid(Id);

			user.setFcmtoken(fcmToken);

			userRepository.save(user);

			response.setIsSuccess(true);
			response.setMessage("Saved");

		} catch (Exception ex) {
			response.setIsSuccess(false);
			response.setMessage(ex.getMessage());
		}
	}
	@Override
	public void addUser(String lastName, String firstName, String houseNo, String address, String pinCode, String city,
			String state, String mobile, String emailAddress, String userUUID, String studentsList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User getUserById(String userUUId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResponse changePassword(String userId, ChangePasswordRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void sendWelcomeEmailToUser() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendWelcomeEmail(User user, boolean keepSchoolInCc) {
		// TODO Auto-generated method stub
		
	}

}
