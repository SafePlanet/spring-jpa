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

import com.spi.uvschool.service.VerificationTokenService;
import com.spi.uvschool.user.api.LostPasswordRequest;
import com.spi.uvschool.user.api.PasswordRequest;

@Path("password")
@Component
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public class PasswordResource {

	@Autowired
	protected VerificationTokenService verificationTokenService;

	@PermitAll
	@Path("tokens")
	@POST
	public Response sendEmailToken(LostPasswordRequest request) {
		verificationTokenService.sendLostPasswordToken(request);
		return Response.ok().build();
	}

	@PermitAll
	@Path("tokens/{token}")
	@POST
	public Response resetPassword(@PathParam("token") String base64EncodedToken, PasswordRequest request) {
		verificationTokenService.resetPassword(base64EncodedToken, request);
		return Response.ok().build();
	}
}
