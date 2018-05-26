/* Copyright (C) SafePlanet Innovations, LLP - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by SafePlanet Innovations <spinnovations.india@gmail.com>, October 2015
 */
package com.spi.uvschool.exception;

/**
 */
public class AuthenticationException extends BaseWebApplicationException {

	private static final long serialVersionUID = 2643693520538146402L;

	public AuthenticationException() {
		super(401, "40102", "Authentication Error",
				"Authentication Error. The username or password were incorrect");
	}

}
