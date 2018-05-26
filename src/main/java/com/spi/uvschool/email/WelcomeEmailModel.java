/* Copyright (C) SafePlanet Innovations, LLP - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by SafePlanet Innovations <spinnovations.india@gmail.com>, October 2015
 */
package com.spi.uvschool.email;

import java.io.Serializable;
import java.util.List;

import com.spi.uvschool.domain.User;
import com.spi.uvschool.domain.VerificationToken;

/**
 *
 * @version 1.0
 * @author:
 * 
 */
public class WelcomeEmailModel implements Serializable {

	private static final long serialVersionUID = 8075622043221781018L;

	private String emailAddress;
	private String userName = null;

	public WelcomeEmailModel(User user, VerificationToken token, String hostNameUrl) {
		this.emailAddress = user.getEmailAddress();
	}

	public WelcomeEmailModel(String userName, String emailId) {
		this.emailAddress = emailId;
		this.userName = userName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

}
