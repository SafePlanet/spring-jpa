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
public class TokenHasExpiredException extends BaseWebApplicationException {

	private static final long serialVersionUID = -7517423081094834665L;

	public TokenHasExpiredException() {
		super(403, "40304", "Token has expired",
				"An attempt was made to load a token that has expired");
	}
}
