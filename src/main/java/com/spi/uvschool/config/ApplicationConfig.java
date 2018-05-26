/* Copyright (C) SafePlanet Innovations, LLP - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by SafePlanet Innovations <spinnovations.india@gmail.com>, October 2015
 */

package com.spi.uvschool.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * User: porter Date: 17/05/2012 Time: 19:07
 */
@Configuration
@PropertySource({ "classpath:/properties/app.properties" })
public class ApplicationConfig {

	private final static String HOSTNAME_PROPERTY = "hostNameUrl";

	private final static String SECURITY_AUTHORIZATION_REQUIRE_SIGNED_REQUESTS = "security.authorization.requireSignedRequests";
	private final static String AUTHORIZATION_EXPIRY_DURATION = "authorization.timeToLive.inSeconds";
	private final static String SESSION_DATE_OFFSET_IN_MINUTES = "session.date.offset.inMinutes";
	private final static String TOKEN_EMAIL_REGISTRATION_DURATION = "token.emailRegistration.timeToLive.inMinutes";
	private final static String TOKEN_EMAIL_VERIFICATION_DURATION = "token.emailVerification.timeToLive.inMinutes";
	private final static String TOKEN_LOST_PASSWORD_DURATION = "token.lostPassword.timeToLive.inMinutes";
	private final static String EMAIL_SERVICES_FROM_ADDRESS = "email.services.fromAddress";
	private final static String EMAIL_SERVICES_REPLYTO_ADDRESS = "email.services.replyTo";
	private final static String EMAIL_SERVICES_VERIFICATION_EMAIL_SUBJECT_TEXT = "email.services.emailVerificationSubjectText";
	private final static String EMAIL_SERVICES_REGISTRATION_EMAIL_SUBJECT_TEXT = "email.services.emailRegistrationSubjectText";
	private final static String EMAIL_SERVICES_COMPLAINT_EMAIL_SUBJECT_TEXT = "email.services.emailComplaintSubjectText";
	private final static String EMAIL_SERVICES_LOST_PASSWORD_SUBJECT_TEXT = "email.services.lostPasswordSubjectText";
	private final static String FCM_AUTH_KEY = "andriod.fcm.firebase.auth.key";
	private final static String FCM_ENABLED = "fcm.enabled";
	private final static String SMS_ENABLED = "sms.enabled";

	private final static String TRANSPORT_ADMIN_EMAIL = "transport.admin.toEmail";

	private final static String USER_IMAGE_DIRECTORY = "user.image.directory";
	private final static String REPORT_DIRECTORY = "user.directory";
	private final static String REVERSE_GEOCODE_ENABLE = "reverse.geocode.enable";

	private final static String REPORT_DB_URL = "database.url";
	private final static String REPORT_DB_URL_BACKUP = "database.urlbackup";
	private final static String REPORT_DB_USERNAME = "database.username";
	private final static String REPORT_DB_PASSWORD = "database.password";
	private final static String REPORT_DB_DRIVER = "database.driverClassName";
	private final static String REPORT_DURATION_IGNORE = "report.duration.ignore.milisec";
	private final static String ALERT_SERVER_LOG_FILE = "alert.server.log.file";
	

	@Autowired
	protected Environment environment;
	
	public int getReportDurationIgnore() {
		return Integer.parseInt(environment.getProperty(REPORT_DURATION_IGNORE));
	}
	
	public String getAlertServerLogFile(){
		return environment.getProperty(ALERT_SERVER_LOG_FILE);
	}

	public String getHostNameUrl() {
		return environment.getProperty(HOSTNAME_PROPERTY);
	}

	public String getFacebookClientId() {
		return environment.getProperty("facebook.clientId");
	}

	public String getFacebookClientSecret() {
		return environment.getProperty("facebook.clientSecret");
	}

	public int getAuthorizationExpiryTimeInSeconds() {
		return Integer.parseInt(environment.getProperty(AUTHORIZATION_EXPIRY_DURATION));
	}

	public int getSessionDateOffsetInMinutes() {
		return Integer.parseInt(environment.getProperty(SESSION_DATE_OFFSET_IN_MINUTES));
	}

	public int getEmailRegistrationTokenExpiryTimeInMinutes() {
		return Integer.parseInt(environment.getProperty(TOKEN_EMAIL_REGISTRATION_DURATION));
	}

	public int getEmailVerificationTokenExpiryTimeInMinutes() {
		return Integer.parseInt(environment.getProperty(TOKEN_EMAIL_VERIFICATION_DURATION));
	}

	public int getLostPasswordTokenExpiryTimeInMinutes() {
		return Integer.parseInt(environment.getProperty(TOKEN_LOST_PASSWORD_DURATION));
	}

	public String getEmailVerificationSubjectText() {
		return environment.getProperty(EMAIL_SERVICES_VERIFICATION_EMAIL_SUBJECT_TEXT);
	}

	public String getEmailRegistrationSubjectText() {
		return environment.getProperty(EMAIL_SERVICES_REGISTRATION_EMAIL_SUBJECT_TEXT);
	}

	public String getLostPasswordSubjectText() {
		return environment.getProperty(EMAIL_SERVICES_LOST_PASSWORD_SUBJECT_TEXT);
	}
	
	public String getFcmAuthKey() {
		return environment.getProperty(FCM_AUTH_KEY);
	}

	public boolean isFcmEnabled() {
		return environment.getProperty(FCM_ENABLED).equalsIgnoreCase("true");
	}
	
	public boolean isSmsEnabled() {
		return environment.getProperty(SMS_ENABLED).equalsIgnoreCase("true");
	}
	
	public String getEmailFromAddress() {
		return environment.getProperty(EMAIL_SERVICES_FROM_ADDRESS);
	}

	public String getEmailReplyToAddress() {
		return environment.getProperty(EMAIL_SERVICES_REPLYTO_ADDRESS);
	}

	public Boolean requireSignedRequests() {
		return environment.getProperty(SECURITY_AUTHORIZATION_REQUIRE_SIGNED_REQUESTS).equalsIgnoreCase("true");
	}

	public String getUserImageDirectory() {
		return environment.getProperty(USER_IMAGE_DIRECTORY);
	}

	public String getReportDirectory() {
		return environment.getProperty(REPORT_DIRECTORY);
	}

	public String getEmailComplaintSubjectText() {
		return environment.getProperty(EMAIL_SERVICES_COMPLAINT_EMAIL_SUBJECT_TEXT);
	}

	public String getTransportAdminEmail() {
		return environment.getProperty(TRANSPORT_ADMIN_EMAIL);
	}

	public String getReportDatabaseUrl() {
		return environment.getProperty(REPORT_DB_URL);
	}
	
	public String getReportDatabaseUrlBackup() {
		return environment.getProperty(REPORT_DB_URL_BACKUP);
	}

	public String getReportDatabaseUserName() {
		return environment.getProperty(REPORT_DB_USERNAME);
	}

	public String getReportDatabasePassword() {
		return environment.getProperty(REPORT_DB_PASSWORD);
	}

	public String getReportDatabaseDriver() {
		return environment.getProperty(REPORT_DB_DRIVER);
	}

	public boolean isReverseGeocodeEnable() {
		if ("on".equalsIgnoreCase(REVERSE_GEOCODE_ENABLE)) {
			return true;
		} else {
			return false;
		}
	}

}
