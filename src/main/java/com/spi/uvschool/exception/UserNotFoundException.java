/* Copyright (C) SafePlanet Innovations, LLP - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by SafePlanet Innovations <spinnovations.india@gmail.com>, October 2015
 */
package com.spi.uvschool.exception;

/**
 *
 * @version 1.0
 * @author:
 * 
 */
public class UserNotFoundException extends BaseWebApplicationException {

	private static final long serialVersionUID = -1555752696078053417L;

	public UserNotFoundException() {
		super(404, "40402", "User Not Found",
				"No User could be found for that Id");
	}
}
