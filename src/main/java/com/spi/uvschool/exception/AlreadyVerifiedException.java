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
public class AlreadyVerifiedException extends BaseWebApplicationException {

	private static final long serialVersionUID = -1701621102248620809L;

	public AlreadyVerifiedException() {
		super(409, "40905", "Already verified",
				"The token has already been verified");
	}
}
