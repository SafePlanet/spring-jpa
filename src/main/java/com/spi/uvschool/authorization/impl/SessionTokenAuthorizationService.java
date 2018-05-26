/* Copyright (C) SafePlanet Innovations, LLP - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by SafePlanet Innovations <spinnovations.india@gmail.com>, October 2015
 */

package com.spi.uvschool.authorization.impl;

import com.spi.uvschool.authorization.AuthorizationRequestContext;
import com.spi.uvschool.dao.UserDAO;
import com.spi.uvschool.domain.AuthorizationToken;
import com.spi.uvschool.domain.User;
import com.spi.uvschool.exception.AuthorizationException;
import com.spi.uvschool.service.AuthorizationService;
import com.spi.uvschool.user.api.ExternalUser;

/**
 *
 * Simple authorization service that requires a session token in the
 * Authorization header This is then matched to a user
 *
 * @version 1.0
 * @author:
 * 
 */
public class SessionTokenAuthorizationService implements AuthorizationService {

	/**
	 * directly access user objects
	 */
	private final UserDAO userRepository;

	public SessionTokenAuthorizationService(UserDAO repository) {
		this.userRepository = repository;
	}

	public ExternalUser authorize(AuthorizationRequestContext securityContext) {
		String token = securityContext.getAuthorizationToken();
		ExternalUser externalUser = null;
		if (token == null) {
			return externalUser;
		}
		User user = userRepository.findBySession(token);
		if (user == null) {
			throw new AuthorizationException("Session token not valid");
		}
		AuthorizationToken authorizationToken = user.getAuthorizationToken();
		if (authorizationToken.getToken().equals(token)) {
			externalUser = new ExternalUser(user);
		}
		return externalUser;
	}
}
