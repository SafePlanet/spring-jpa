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
public class TokenNotFoundException extends BaseWebApplicationException {

	private static final long serialVersionUID = -6819852038732082394L;

	public TokenNotFoundException() {
		super(404, "40407", "Token Not Found",
				"No token could be found for that Id");
	}
}
