/* Copyright (C) SafePlanet Innovations, LLP - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by SafePlanet Innovations <spinnovations.india@gmail.com>, October 2015
 */
package com.spi.uvschool.service;

import com.spi.uvschool.domain.VerificationToken;
import com.spi.uvschool.user.api.LostPasswordRequest;
import com.spi.uvschool.user.api.PasswordRequest;

/**
 *
 * @version 1.0
 * @author:
 * 
 */
public interface VerificationTokenService {

	public VerificationToken sendEmailVerificationToken(String userId);

	public VerificationToken sendEmailRegistrationToken(String userId);

	public VerificationToken sendLostPasswordToken(
			LostPasswordRequest lostPasswordRequest);

	public VerificationToken verify(String base64EncodedToken);

	public VerificationToken generateEmailVerificationToken(String emailAddress);

	public VerificationToken resetPassword(String base64EncodedToken,
			PasswordRequest passwordRequest);
}
