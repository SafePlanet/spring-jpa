/* Copyright (C) SafePlanet Innovations, LLP - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by SafePlanet Innovations <spinnovations.india@gmail.com>, October 2015
 */
package com.spi.uvschool.user.api;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author:
 */
@XmlRootElement
public class AuthenticatedUserToken {

	private String userId;
	private String token;
	private String message;

	public AuthenticatedUserToken() {
	}

	public AuthenticatedUserToken(String userId, String sessionToken) {
		this.userId = userId;
		this.token = sessionToken;
		
	}
	public AuthenticatedUserToken(String userId, String sessionToken,String message) {
		this.userId = userId;
		this.token = sessionToken;
		this.message=message;
	}

	public String getUserId() {
		return userId;
	}

	public String getToken() {
		return token;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
