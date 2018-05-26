/* Copyright (C) SafePlanet Innovations, LLP - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by SafePlanet Innovations <spinnovations.india@gmail.com>, October 2015
 */
package com.spi.uvschool.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "email_template")
public class EmailTemplate extends AbstractPersistable<Long> {

	private static final long serialVersionUID = 5763144867458714737L;

	String name = null;
	String vmUrl = null;
	char userInitiated;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVmUrl() {
		return vmUrl;
	}

	public void setVmUrl(String vmUrl) {
		this.vmUrl = vmUrl;
	}

	public char getUserInitiated() {
		return userInitiated;
	}

	public void setUserInitiated(char userInitiated) {
		this.userInitiated = userInitiated;
	}
}
