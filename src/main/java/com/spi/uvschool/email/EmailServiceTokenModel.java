/* Copyright (C) SafePlanet Innovations, LLP - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by SafePlanet Innovations <spinnovations.india@gmail.com>, October 2015
 */
package com.spi.uvschool.email;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;

import com.spi.uvschool.domain.User;
import com.spi.uvschool.domain.VerificationToken;
import com.spi.uvschool.user.api.ExternalUser;

/**
 *
 * @version 1.0
 * @author:
 * 
 */
public class EmailServiceTokenModel implements Serializable {

	private static final long serialVersionUID = -6706997308126703420L;
	private String toEmailAddress = null;
	private String ccEmailAddress = null;
	private String bccEmailAddress = null;
	private String token;
	private VerificationToken.VerificationTokenType tokenType;
	private String hostNameUrl;
	private String emailSubject = null;
	private String emailBody = null;

	private String lostPassword;

	private String userName;

	public EmailServiceTokenModel(User user, VerificationToken token, String hostNameUrl) {
		this.toEmailAddress = user.getEmailAddress();
		this.token = token.getToken();
		this.tokenType = token.getTokenType();
		this.hostNameUrl = hostNameUrl;
		this.userName = user.getFirstName() + " " + user.getLastName();

	}

	public EmailServiceTokenModel(User user, VerificationToken token, String hostNameUrl, String lostPassword) {
		this.toEmailAddress = user.getEmailAddress();
		this.token = token.getToken();
		this.tokenType = token.getTokenType();
		this.hostNameUrl = hostNameUrl;
		this.userName = user.getFirstName() + " " + user.getLastName();
		this.lostPassword = lostPassword;

	}

	// For Custom Email
	public EmailServiceTokenModel(VerificationToken token, String userName, String emailAddress, String schoolName,
			String schoolContact, String schoolAddress, String schoolEmailAddress, String emailSubject,
			String emailBody) {
		this.token = token.getToken();
		this.tokenType = token.getTokenType();
		this.toEmailAddress = emailAddress;
		this.userName = userName;
		this.emailSubject = emailSubject;
		this.emailBody = emailBody;
	}

	// For Welcome Email
	public EmailServiceTokenModel(VerificationToken token, String userName, String emailAddress, String schoolName,
			String schoolContact, String schoolAddress, String schoolEmailAddress, String wayPointLink) {
		this.token = token.getToken();
		this.tokenType = token.getTokenType();
		this.toEmailAddress = emailAddress;
		this.userName = userName;
	}

	public EmailServiceTokenModel(ExternalUser user, VerificationToken token, String hostNameUrl, String userName,
			String complaintMessage, String emailId, String schoolEmailAddress) {
		this.toEmailAddress = emailId;
		this.ccEmailAddress = user.getEmailAddress();
		this.token = token.getToken();
		this.tokenType = token.getTokenType();
		this.hostNameUrl = hostNameUrl;
		this.userName = userName;
	}

	public String getEncodedToken() {
		return new String(Base64.encodeBase64(token.getBytes()));
	}

	public String getToken() {
		return token;
	}

	public VerificationToken.VerificationTokenType getTokenType() {
		return tokenType;
	}

	public String getHostNameUrl() {
		return hostNameUrl;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmailBody() {
		return emailBody;
	}

	public void setEmailBody(String emailBody) {
		this.emailBody = emailBody;
	}

	public String getEmailSubject() {
		return emailSubject;
	}

	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void setTokenType(VerificationToken.VerificationTokenType tokenType) {
		this.tokenType = tokenType;
	}

	public void setHostNameUrl(String hostNameUrl) {
		this.hostNameUrl = hostNameUrl;
	}

	public String getToEmailAddress() {
		return toEmailAddress;
	}

	public String getCcEmailAddress() {
		return ccEmailAddress;
	}

	public String getBccEmailAddress() {
		return bccEmailAddress;
	}

	public String getLostPassword() {
		return lostPassword;
	}

	public void setLostPassword(String lostPassword) {
		this.lostPassword = lostPassword;
	}

}
