/* Copyright (C) SafePlanet Innovations, LLP - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by SafePlanet Innovations <spinnovations.india@gmail.com>, October 2015
 */
package com.spi.uvschool.exception;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.spi.uvschool.api.ErrorResponse;
import com.spi.uvschool.api.ValidationError;

/**
 * @version 1.0
 * @author:
 * 
 */
public abstract class BaseWebApplicationException extends
		WebApplicationException {

	private static final long serialVersionUID = -8509516677146661917L;
	private final int status;
	private final String errorMessage;
	private final String errorCode;
	private final String developerMessage;

	public BaseWebApplicationException(int httpStatus, String errorCode,
			String errorMessage, String developerMessage) {
		this.status = httpStatus;
		this.errorMessage = errorMessage;
		this.errorCode = errorCode;
		this.developerMessage = developerMessage;
	}

	@Override
	public Response getResponse() {
		return Response.status(status).type(MediaType.APPLICATION_JSON_TYPE)
				.entity(getErrorResponse()).build();
	}

	public ErrorResponse getErrorResponse() {
		ErrorResponse response = new ErrorResponse();
		response.setErrorCode(errorCode);
		response.setApplicationMessage(developerMessage);
		response.setConsumerMessage(errorMessage);
                ValidationError error = new ValidationError();
                error.setMessage(errorMessage);
                List<ValidationError> errors = new ArrayList<ValidationError>();
                errors.add(error);
		response.setValidationErrors(errors);
		return response;
	}

}
