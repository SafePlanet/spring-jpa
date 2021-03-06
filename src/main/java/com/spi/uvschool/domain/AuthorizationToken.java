/* Copyright (C) SafePlanet Innovations, LLP - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by SafePlanet Innovations <spinnovations.india@gmail.com>, October 2015
 */
package com.spi.uvschool.domain;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

/**
 *
 * @version 1.0
 * @author:
 * 
 */
@Entity
@Table(name = "authorization_token")
public class AuthorizationToken extends AbstractPersistable<Long> {

	private static final long serialVersionUID = -133640488768408993L;
	private final static Integer DEFAULT_TIME_TO_LIVE_IN_SECONDS = (60 * 60 * 24 * 30); // 30
																						// Days

	@Column(length = 36)
	private String token;

	private Date timeCreated;

	private Date expirationDate;

	@JoinColumn(name = "user_id")
	@OneToOne(fetch = FetchType.LAZY)
	private User user;

	public AuthorizationToken() {
	}

	public AuthorizationToken(User user) {
		this(user, DEFAULT_TIME_TO_LIVE_IN_SECONDS);
	}

	public AuthorizationToken(User user, Integer timeToLiveInSeconds) {
		this.token = UUID.randomUUID().toString();
		this.user = user;
		this.timeCreated = new Date();
		this.expirationDate = new Date(System.currentTimeMillis()
				+ (timeToLiveInSeconds * 1000L));
	}

	public boolean hasExpired() {
		return this.expirationDate != null
				&& this.expirationDate.before(new Date());
	}

	public String getToken() {
		return token;
	}

	public User getUser() {
		return user;
	}

	public Date getTimeCreated() {
		return timeCreated;
	}

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
        
        
}
