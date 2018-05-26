/* Copyright (C) SafePlanet Innovations, LLP - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by SafePlanet Innovations <spinnovations.india@gmail.com>, October 2015
 */
package com.spi.uvschool.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.Map;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spi.uvschool.config.ApplicationConfig;
import com.spi.uvschool.domain.Role;
import com.spi.uvschool.exception.AuthorizationException;
import com.spi.uvschool.exception.ValidationException;
import com.spi.uvschool.gateway.EmailServicesGateway;
import com.spi.uvschool.service.UserService;
import com.spi.uvschool.service.VerificationTokenService;
import com.spi.uvschool.user.api.AuthenticatedUserToken;
import com.spi.uvschool.user.api.ChangePasswordRequest;
import com.spi.uvschool.user.api.CreateUserRequest;
import com.spi.uvschool.user.api.DashboardLinks;
import com.spi.uvschool.user.api.ExternalUser;
import com.spi.uvschool.user.api.LoginRequest;
import com.spi.uvschool.user.api.OAuth2Request;
import com.spi.uvschool.user.api.UpdateUserRequest;
import com.spi.uvschool.util.StringUtil;
import com.sun.jersey.api.view.Viewable;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

/**
 */
@Path("/user")
@Component
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON, MediaType.MULTIPART_FORM_DATA })
public class UserResource {

	private static final Logger LOG = LoggerFactory.getLogger(UserResource.class);

	private ConnectionFactoryLocator connectionFactoryLocator;

	@Autowired
	protected UserService userService;

	@Autowired
	protected VerificationTokenService verificationTokenService;

	@Autowired
	ApplicationConfig applicationConfig;

	@Context
	protected UriInfo uriInfo;

	@Autowired
	ApplicationConfig config;

	public UserResource() {
	}

	@Autowired
	public UserResource(ConnectionFactoryLocator connectionFactoryLocator) {
		this.connectionFactoryLocator = connectionFactoryLocator;
	}

	@PermitAll
	@Path("signup")
	@POST
	public Response signupUser(CreateUserRequest request) {
		AuthenticatedUserToken token = userService.createUser(request, Role.authenticated);
		verificationTokenService.sendEmailRegistrationToken(token.getUserId());
		URI location = uriInfo.getAbsolutePathBuilder().path(token.getUserId()).build();
		return Response.created(location).entity(token).build();
	}

	@RolesAllowed({ "authenticated", "administrator", "teacher", "superAdmin", "transporter" })
	@Path("{userId}")
	@DELETE
	public Response deleteUser(@Context SecurityContext sc, @PathParam("userId") String userId) {
		ExternalUser userMakingRequest = (ExternalUser) sc.getUserPrincipal();
		userService.deleteUser(userMakingRequest, userId);
		return Response.ok().build();
	}

	@PermitAll
	@Path("saveFCMToken/{userId}/{fcmToken}")
	@GET
	public Response saveFCMToken(@PathParam("userId") String userId, @PathParam("fcmToken") String fcmToken) {

		userService.saveFCMToken(fcmToken, userId);
		return Response.ok().build();
	}

	@PermitAll
	@Path("login/")
	@POST
	public Response login(LoginRequest request, @Context HttpServletRequest httpRequest) {

		AuthenticatedUserToken token = userService.login(request);
		if(token.getUserId()!=null){
		Role userRole = userService.getUserRole(token.getUserId());
		if (token != null && token.getToken() != null
				&& (userRole.equals(Role.administrator) || userRole.equals(Role.superAdmin))) {
			httpRequest.getSession().setAttribute("AUTH_TOKEN", token);
			httpRequest.getSession().setAttribute("IS_ADMIN", "Yes");
			httpRequest.getSession().setAttribute("USER_ID", token.getUserId());
		}
		LOG.debug(request.getUsername() + " user got access to the system.");
		}
		return getLoginResponse(token);
	}

	@PermitAll
	@Path("changePassword/{userId}")
	@PUT
	public Response changePassword(@Context SecurityContext sc, @PathParam("userId") String userId,
			ChangePasswordRequest cpRequest) {

		if (!cpRequest.Validate()) {
			Response.serverError().build();
		}

		return Response.ok().entity(userService.changePassword(userId, cpRequest)).build();
	}

	@PermitAll
	@Path("login/{providerId}")
	@POST
	public Response socialLogin(@PathParam("providerId") String providerId, OAuth2Request request) {
		OAuth2ConnectionFactory<?> connectionFactory = (OAuth2ConnectionFactory<?>) connectionFactoryLocator
				.getConnectionFactory(providerId);
		Connection<?> connection = connectionFactory.createConnection(new AccessGrant(request.getAccessToken()));
		AuthenticatedUserToken token = userService.socialLogin(connection);
		return getLoginResponse(token);
	}

	@RolesAllowed({ "authenticated", "administrator", "teacher", "individual", "superAdmin", "transporter" })
	@Path("{userId}")
	@GET
	public Response getUser(@Context SecurityContext sc, @PathParam("userId") String userId) {
		ExternalUser userMakingRequest = (ExternalUser) sc.getUserPrincipal();
		ExternalUser user = userService.getUser(userMakingRequest, userId);
		return Response.ok().entity(user).build();
	}

	@RolesAllowed({ "authenticated", "administrator", "teacher", "individual", "superAdmin", "transporter" })
	@Path("saveUserDetails/{userId}")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@POST
	public Response saveUserDetails(@Context SecurityContext sc, @PathParam("userId") String userId,
			@FormDataParam("userPhoto") InputStream fileInputStream,
			@FormDataParam("userPhoto") FormDataContentDisposition contentDispositionHeader,
			@FormDataParam("userDetails") String userDetails) {

		String fileName = contentDispositionHeader.getFileName();
		String filePath = applicationConfig.getUserImageDirectory() + fileName;
		Map paramValues = contentDispositionHeader.getParameters();

		ObjectMapper mapper = new ObjectMapper();
		// mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		UpdateUserRequest request = null;
		try {
			request = mapper.readValue(userDetails, UpdateUserRequest.class);
			request.setUserImage(fileName);

		} catch (Exception e) {
			LOG.error("Error in fetching user Image ", e);
		}

		if (StringUtil.isValid(fileName)) {
			saveFile(fileInputStream, filePath);
		}
		ExternalUser userMakingRequest = (ExternalUser) sc.getUserPrincipal();

		if (!userMakingRequest.getId().equals(userId)) {
			throw new AuthorizationException("User not authorized to modify this profile");
		}

		try {
			if (request.getMobile() != null && !request.getMobile().trim().equals("")
					&& !"null".equals(request.getMobile())) {
				Long.parseLong(request.getMobile());
			}
		} catch (Exception e) {
			LOG.error("Invalid Mobile Number.", e);
			throw new ValidationException("Invalid Mobile Number.", "mobile");
		}

		ExternalUser savedUser = null;
		if (request != null) {
			boolean sendVerificationToken = StringUtils.hasLength(request.getEmailAddress())
					&& !request.getEmailAddress().equals(userMakingRequest.getEmailAddress());
			savedUser = userService.updateUser(userId, request);
			if (sendVerificationToken) {
				verificationTokenService.sendEmailVerificationToken(savedUser.getId());
			}
		}
		return Response.status(200).entity(savedUser).build();
		// return Response.ok().build();
	}

	// save uploaded file to a defined location on the server

	private void saveFile(InputStream uploadedInputStream, String serverLocation) {
		try {
			OutputStream outpuStream = new FileOutputStream(new File(serverLocation));
			int read = 0;
			byte[] bytes = new byte[1024];
			outpuStream = new FileOutputStream(new File(serverLocation));
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				outpuStream.write(bytes, 0, read);
			}
			outpuStream.flush();
			outpuStream.close();
		} catch (IOException e) {
			LOG.error("Error while saving the file", e);
		}
	}

	@RolesAllowed({ "authenticated", "administrator", "teacher", "individual", "superAdmin", "transporter" })
	@Path("{userId}")
	@PUT
	public Response updateUser(@Context SecurityContext sc, @PathParam("userId") String userId,
			UpdateUserRequest request) {
		ExternalUser userMakingRequest = (ExternalUser) sc.getUserPrincipal();

		if (!userMakingRequest.getId().equals(userId)) {
			throw new AuthorizationException("User not authorized to modify this profile");
		}

		boolean sendVerificationToken = StringUtils.hasLength(request.getEmailAddress())
				&& !request.getEmailAddress().equals(userMakingRequest.getEmailAddress());
		ExternalUser savedUser = userService.saveUser(userId, request);
		if (sendVerificationToken) {
			verificationTokenService.sendEmailVerificationToken(savedUser.getId());
		}
		return Response.ok().build();
	}

	private Response getLoginResponse(AuthenticatedUserToken token) {
		URI location = UriBuilder.fromPath(uriInfo.getBaseUri() + "user/" + token.getUserId()).build();
		return Response.ok().entity(token).contentLocation(location).build();
	}


	private boolean deviceUserAgent(String userAgent) {

		String[] devices = { "iPhone", "iPod", "iPad", "Android", "BlackBerry" };

		for (String device : devices) {
			if (userAgent.contains(device)) {
				return true;
			}
		}
		return false;

	}

	@GET
	@Path("userHome/{userId}")
	@RolesAllowed({ "authenticated", "teacher", "administrator", "individual", "superAdmin", "transporter" })
	@Produces(MediaType.TEXT_HTML)
	public Response getUserHome(@HeaderParam("user-agent") String userAgent, @Context SecurityContext sc,
			@PathParam("userId") String userId) {
		ExternalUser userMakingRequest = (ExternalUser) sc.getUserPrincipal();
		DashboardLinks dashboardLinks = new DashboardLinks(userMakingRequest.getRole(), deviceUserAgent(userAgent));
		userMakingRequest.setApp(deviceUserAgent(userAgent));
		return Response.ok(new Viewable("/welcome", userMakingRequest)).build();
	}

	@Path("userPicture/{userId}")
	@Produces({ "image/png", "image/jpg", "image/gif" })
	public Response getFullImage(@Context SecurityContext sc, @PathParam("userId") String userId) {
		ExternalUser userMakingRequest = (ExternalUser) sc.getUserPrincipal();

		File userImage = new File(applicationConfig.getUserImageDirectory() + userMakingRequest.getUserImage());

		// uncomment line below to send non-streamed
		return Response.ok(userImage).build();

		// uncomment line below to send streamed
		// return Response.ok(new ByteArrayInputStream(imageData)).build();
	}

}
