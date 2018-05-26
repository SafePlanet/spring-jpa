/* Copyright (C) SafePlanet Innovations, LLP - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by SafePlanet Innovations <spinnovations.india@gmail.com>, October 2015
 */
package com.spi.uvschool.service;

import com.spi.uvschool.authorization.AuthorizationRequestContext;
import com.spi.uvschool.user.api.ExternalUser;

/**
 *
 * @author:
 */
public interface AuthorizationService {

	/**
	 * Given an AuthorizationRequestContext validate and authorize a User
	 *
	 * @param authorizationRequestContext
	 *            the context required to authorize a user for a particular
	 *            request
	 * @return ExternalUser
	 */
	public ExternalUser authorize(
			AuthorizationRequestContext authorizationRequestContext);
}
