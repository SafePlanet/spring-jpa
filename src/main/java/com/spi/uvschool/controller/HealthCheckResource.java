/* Copyright (C) SafePlanet Innovations, LLP - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by SafePlanet Innovations <spinnovations.india@gmail.com>, October 2015
 */
package com.spi.uvschool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * 
 */
@Path("/healthcheck")
@Component
@Produces({ MediaType.TEXT_PLAIN })
@PropertySource("classpath:properties/app.properties")
public class HealthCheckResource {

	@Autowired
	Environment env;

	@PermitAll
	@GET
	public Response ping() {
		return Response
				.ok()
				.entity("Running version "
						+ env.getProperty("application.version")).build();
	}

}
