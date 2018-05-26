/* Copyright (C) SafePlanet Innovations, LLP - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by SafePlanet Innovations <spinnovations.india@gmail.com>, October 2015
 */
package com.spi.uvschool.email;

/**
 *
 * @version 1.0
 * @author:
 * 
 */
public interface MailSenderService {

	public EmailServiceTokenModel sendVerificationEmail(EmailServiceTokenModel emailServiceTokenModel);

	public EmailServiceTokenModel sendRegistrationEmail(EmailServiceTokenModel emailServiceTokenModel);

	public EmailServiceTokenModel sendLostPasswordEmail(EmailServiceTokenModel emailServiceTokenModel);

	public EmailServiceTokenModel sendWelcomeMessageEmail(EmailServiceTokenModel emailServiceTokenModel);
	
	public EmailServiceTokenModel sendCustomMessageEmail(final EmailServiceTokenModel emailServiceTokenModel);

}
