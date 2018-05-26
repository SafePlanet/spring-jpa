/* Copyright (C) SafePlanet Innovations, LLP - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by SafePlanet Innovations <spinnovations.india@gmail.com>, October 2015
 */
package com.spi.uvschool.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * 
 */
@Provider
public class GenericExceptionMapper implements ExceptionMapper<Exception> {

	private static Logger LOG = LoggerFactory
			.getLogger(GenericExceptionMapper.class);

	public Response toResponse(Exception exception) {
		if (exception instanceof WebApplicationException) {
			LOG.info("Web Application Exception: " + exception);
			return ((WebApplicationException) exception).getResponse();
		}
		LOG.error("Internal Server Error: " + exception);
		LOG.error("Internal Server Error: " + exception.getCause());
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
	}
}
