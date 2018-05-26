/* Copyright (C) SafePlanet Innovations, LLP - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by SafePlanet Innovations <spinnovations.india@gmail.com>, October 2015
 */
package com.spi.uvschool.controller;

import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spi.uvschool.user.api.EmailVerificationRequest;
import com.spi.uvschool.service.VerificationTokenService;

/**
 * @version 1.0
 * @author:
 * 
 */
@Path("verify")
@Component
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public class VerificationResource {

	@Autowired
	protected VerificationTokenService verificationTokenService;
	
	public VerificationResource(){}

	@PermitAll
	@Path("tokens/{token}")
	@POST
	public Response verifyToken(@PathParam("token") String token) {
		verificationTokenService.verify(token);
		return Response.ok().build();
	}

	@PermitAll
	@Path("tokens")
	@POST
	public Response sendEmailToken(EmailVerificationRequest request) {
		verificationTokenService.generateEmailVerificationToken(request
				.getEmailAddress());
		return Response.ok().build();
	}
}
