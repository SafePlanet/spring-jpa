/* Copyright (C) SafePlanet Innovations, LLP - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by SafePlanet Innovations <spinnovations.india@gmail.com>, October 2015
 */
package com.spi.uvschool.gateway;

import com.spi.uvschool.email.EmailServiceTokenModel;

/**
 *
 * @version 1.0
 * @author:
 * 
 */
public interface EmailServicesGateway {

	public void sendVerificationToken(EmailServiceTokenModel model);
}
