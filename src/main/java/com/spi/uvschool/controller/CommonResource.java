/* Copyright (C) SafePlanet Innovations, LLP - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by SafePlanet Innovations <spinnovations.india@gmail.com>, October 2015
 */
package com.spi.uvschool.controller;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.spi.uvschool.gateway.EmailServicesGateway;
import com.spi.uvschool.service.CommonService;
import com.spi.uvschool.service.UserService;
import com.spi.uvschool.service.VerificationTokenService;
import com.spi.uvschool.user.api.SendEmailModel;
import com.spi.uvschool.vm.EmailTemplateVM;
import com.spi.uvschool.vm.MessageTemplateVM;

@Path("system")
@Component
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
@Transactional
public class CommonResource {

	private ConnectionFactoryLocator connectionFactoryLocator;

	@Autowired
	protected VerificationTokenService verificationTokenService;

	@Autowired
	protected EmailServicesGateway emailServicesGateway;
	
	@Autowired
	public CommonService commonService;
	
	@Autowired
	public UserService userService;

	@Context
	protected UriInfo uriInfo;
	
	public CommonResource(){}

	@Autowired
	public CommonResource(ConnectionFactoryLocator connectionFactoryLocator) {
		this.setConnectionFactoryLocator(connectionFactoryLocator);
	}
	
	public ConnectionFactoryLocator getConnectionFactoryLocator() {
		return connectionFactoryLocator;
	}

	public void setConnectionFactoryLocator(ConnectionFactoryLocator connectionFactoryLocator) {
		this.connectionFactoryLocator = connectionFactoryLocator;
	}
	
	@RolesAllowed({"administrator"})
	@GET
	@Path("getAllEmailTemplates")
	public Response getAllEmailTemplates(@Context SecurityContext sc) {
		List<EmailTemplateVM> emailTemplates = commonService.getAllEmailTemplates();
		return Response.ok().entity(emailTemplates).build();
	}
	
	
}
