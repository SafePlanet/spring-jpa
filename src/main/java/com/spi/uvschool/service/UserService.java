/* Copyright (C) SafePlanet Innovations, LLP - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by SafePlanet Innovations <spinnovations.india@gmail.com>, October 2015
 */
package com.spi.uvschool.service;

import org.springframework.social.connect.Connection;

import com.spi.uvschool.api.ServiceResponse;
import com.spi.uvschool.domain.AuthorizationToken;
import com.spi.uvschool.domain.Role;
import com.spi.uvschool.domain.User;
import com.spi.uvschool.user.api.AuthenticatedUserToken;
import com.spi.uvschool.user.api.ChangePasswordRequest;
import com.spi.uvschool.user.api.CreateUserRequest;
import com.spi.uvschool.user.api.ExternalUser;
import com.spi.uvschool.user.api.LoginRequest;
import com.spi.uvschool.user.api.UpdateUserRequest;

/**
 * @author:
 *
 * 			Service to manage users
 */
public interface UserService {

	/**
	 * Create a new User with the given role
	 *
	 * @param request
	 * @param role
	 * @return AuthenticatedUserToken
	 */
	public AuthenticatedUserToken createUser(CreateUserRequest request, Role role);

	/**
	 * Create a Default User with a given role
	 *
	 * @param role
	 * @return AuthenticatedUserToken
	 */
	public AuthenticatedUserToken createUser(Role role);

	/**
	 * Login a User
	 *
	 * @param request
	 * @return AuthenticatedUserToken
	 */
	public AuthenticatedUserToken login(LoginRequest request);

	/**
	 * Log in a User using Connection details from an authorized request from
	 * the User's supported Social provider encapsulated in the
	 * {@link org.springframework.social.connect.Connection} parameter
	 *
	 * @param connection
	 *            containing the details of the authorized user account form the
	 *            Social provider
	 * @return the User account linked to the {@link com.spi.domain.SocialUser}
	 *         account
	 */
	public AuthenticatedUserToken socialLogin(Connection<?> connection);

	/**
	 * Get a User based on a unique identifier
	 *
	 * Identifiers supported are uuid, emailAddress
	 *
	 * @param userIdentifier
	 * @return User
	 */
	public ExternalUser getUser(ExternalUser requestingUser, String userIdentifier);

	/**
	 * Delete user, only authenticated user accounts can be deleted
	 *
	 * @param userMakingRequest
	 *            the user authorized to delete the user
	 * @param userId
	 *            the id of the user to delete
	 */
	public void deleteUser(ExternalUser userMakingRequest, String userId);

	/**
	 * Save User
	 *
	 * @param userId
	 * @param request
	 */
	public ExternalUser saveUser(String userId, UpdateUserRequest request);

	/**
	 * Create an AuthorizationToken for the User
	 *
	 * @return
	 */
	public AuthorizationToken createAuthorizationToken(User user);

	/**
	 * Update User
	 *
	 * @param userId
	 * @param request
	 */
	public ExternalUser updateUser(String userId, UpdateUserRequest request);


	public void addUser(String lastName, String firstName, String houseNo, String address, String pinCode, String city, String state, String mobile,
			String emailAddress, String userUUID, String studentsList);

	public User getUserById(String userUUId);

	public ServiceResponse changePassword(String userId, ChangePasswordRequest request);

	public void sendWelcomeEmailToUser();

	public void sendWelcomeEmail(User user, boolean keepSchoolInCc);

	public void saveFCMToken(String fcmToken, String userId);
	
	public Role getUserRole(String userUUID);
}
