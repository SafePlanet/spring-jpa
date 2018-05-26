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

import com.spi.uvschool.config.ApplicationConfig;
import com.spi.uvschool.service.StatesService;
import com.spi.uvschool.user.api.ExternalUser;
import com.spi.uvschool.vm.StateCityVM;

/**
 */
@Path("/states")
@Component
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public class StateResource {

	private ConnectionFactoryLocator connectionFactoryLocator;

	@Autowired
	protected StatesService statesService;

	@Context
	protected UriInfo uriInfo;

	@Autowired
	ApplicationConfig config;

	public StateResource(){}
	
	@Autowired
	public StateResource(ConnectionFactoryLocator connectionFactoryLocator) {
		this.connectionFactoryLocator = connectionFactoryLocator;
	}

	@RolesAllowed({"authenticated", "administrator", "teacher", "superAdmin"})
	@Path("getStates/{userId}")
	@GET
	public Response getStates(@Context SecurityContext sc, @PathParam("userId") String userId) {
		ExternalUser userMakingRequest = (ExternalUser) sc.getUserPrincipal();
                
		List<StateCityVM> states = statesService.getStatesList();
                
		return Response.ok().entity(states).build();
	}
	@RolesAllowed({"authenticated", "administrator", "teacher", "superAdmin"})
	@Path("getCityList/{userId}/{stateId}")
	@GET
	public Response getCityList(@Context SecurityContext sc, @PathParam("userId") String userId ,@PathParam("stateId") long stateId) {
		ExternalUser userMakingRequest = (ExternalUser) sc.getUserPrincipal();
                
		List<StateCityVM> cities = statesService.getCityList(stateId);
                
		return Response.ok().entity(cities).build();
	}
	
 
	
}
