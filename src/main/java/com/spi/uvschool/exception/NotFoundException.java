/* Copyright (C) SafePlanet Innovations, LLP - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by SafePlanet Innovations <spinnovations.india@gmail.com>, October 2015
 */
package com.spi.uvschool.exception;

import javax.ws.rs.WebApplicationException;

/**
 * 
 */
public class NotFoundException extends WebApplicationException {

	private static final long serialVersionUID = 649535115392562701L;

	public NotFoundException() {
		super(404);
	}
}
