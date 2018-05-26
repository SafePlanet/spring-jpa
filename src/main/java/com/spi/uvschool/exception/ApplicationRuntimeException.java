/* Copyright (C) SafePlanet Innovations, LLP - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by SafePlanet Innovations <spinnovations.india@gmail.com>, October 2015
 */
package com.spi.uvschool.exception;

public class ApplicationRuntimeException extends BaseWebApplicationException {

	private static final long serialVersionUID = 246769280668873398L;

	public ApplicationRuntimeException(String applicationMessage) {
		super(500, "50002", "Internal System error", applicationMessage);
	}
}
