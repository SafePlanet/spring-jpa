/* Copyright (C) SafePlanet Innovations, LLP - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by SafePlanet Innovations <spinnovations.india@gmail.com>, October 2015
 */
package com.spi.uvschool.service;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import com.spi.uvschool.exception.ValidationException;

/**
 * @version 1.0
 * @author:
 * 
 */
public abstract class BaseService {

	private Validator validator;

	public BaseService(Validator validator) {
		this.validator = validator;
	}

	protected void validate(Object request) {
		Set<? extends ConstraintViolation<?>> constraintViolations = validator.validate(request);
		if (constraintViolations.size() > 0) {
			throw new ValidationException(constraintViolations);
		}
	}

}
