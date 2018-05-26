/* Copyright (C) SafePlanet Innovations, LLP - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by SafePlanet Innovations <spinnovations.india@gmail.com>, October 2015
 */
package com.spi.uvschool.user.social;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.util.StringUtils;

import com.spi.uvschool.dao.SocialUserDAO;
import com.spi.uvschool.dao.UserDAO;
import com.spi.uvschool.domain.Role;
import com.spi.uvschool.domain.SocialUser;
import com.spi.uvschool.domain.User;
import com.spi.uvschool.service.UserService;

/**
 */
public class JpaUsersConnectionRepository implements UsersConnectionRepository {

	private SocialUserDAO socialUserRepository;

	private UserService userService;

	private UserDAO userRepository;

	private final ConnectionFactoryLocator connectionFactoryLocator;

	private final TextEncryptor textEncryptor;

	public JpaUsersConnectionRepository(final SocialUserDAO repository,
			final UserDAO userRepository,
			final ConnectionFactoryLocator connectionFactoryLocator,
			final TextEncryptor textEncryptor) {
		this.socialUserRepository = repository;
		this.userRepository = userRepository;
		this.connectionFactoryLocator = connectionFactoryLocator;
		this.textEncryptor = textEncryptor;

	}

	/**
	 * Find User with the Connection profile (providerId and providerUserId) If
	 * this is the first connection attempt there will be nor User so create one
	 * and persist the Connection information In reality there will only be one
	 * User associated with the Connection
	 *
	 * @param connection
	 * @return List of User Ids (see User.getUuid())
	 */
	public List<String> findUserIdsWithConnection(Connection<?> connection) {
		List<String> userIds = new ArrayList<String>();
		ConnectionKey key = connection.getKey();
		List<SocialUser> users = socialUserRepository
				.findByProviderIdAndProviderUserId(key.getProviderId(),
						key.getProviderUserId());
		if (!users.isEmpty()) {
			for (SocialUser user : users) {
				userIds.add(user.getUser().getUuid().toString());
			}
			return userIds;
		}
		// First time connected so create a User account or find one that is
		// already created with the email address
		User user = findUserFromSocialProfile(connection);
		String userId;
		if (user == null) {
			userId = userService.createUser(Role.authenticated).getUserId();
		} else {
			userId = user.getUuid().toString();
		}
		// persist the Connection
		createConnectionRepository(userId).addConnection(connection);
		userIds.add(userId);

		return userIds;
	}

	public Set<String> findUserIdsConnectedTo(String providerId,
			Set<String> providerUserIds) {
		return socialUserRepository.findByProviderIdAndProviderUserId(
				providerId);
	}

	public ConnectionRepository createConnectionRepository(String userId) {
		if (userId == null) {
			throw new IllegalArgumentException("userId cannot be null");
		}
		User user = userRepository.findByUuid(userId);
		if (user == null) {
			throw new IllegalArgumentException("User not Found");
		}
		return new JpaConnectionRepository(socialUserRepository,
				userRepository, user, connectionFactoryLocator, textEncryptor);
	}

	private User findUserFromSocialProfile(Connection connection) {
		User user = null;
		UserProfile profile = connection.fetchUserProfile();
		if (profile != null && StringUtils.hasText(profile.getEmail())) {
			user = userRepository.findByEmailAddress(profile.getEmail());
		}
		return user;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
