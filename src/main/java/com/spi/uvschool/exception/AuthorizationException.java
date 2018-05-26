/* Copyright (C) SafePlanet Innovations, LLP - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by SafePlanet Innovations <spinnovations.india@gmail.com>, October 2015
 */
package com.spi.uvschool.exception;

/**
 */
public class AuthorizationException extends BaseWebApplicationException {

	private static final long serialVersionUID = -3028152283910267172L;

	public AuthorizationException(String applicationMessage) {
		super(403, "40301", "Not authorized", applicationMessage);
	}

}
