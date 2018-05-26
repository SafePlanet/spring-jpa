/* Copyright (C) SafePlanet Innovations, LLP - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by SafePlanet Innovations <spinnovations.india@gmail.com>, October 2015
 */
package com.spi.uvschool.exception;

/**
 */
public class DuplicateUserException extends BaseWebApplicationException {

	private static final long serialVersionUID = -5440104863565203561L;

	public DuplicateUserException() {
		super(409, "40901", "User already exists",
				"An attempt was made to create a user that already exists");
	}
}
