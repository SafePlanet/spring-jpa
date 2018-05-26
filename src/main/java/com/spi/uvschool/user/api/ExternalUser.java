/* Copyright (C) SafePlanet Innovations, LLP - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by SafePlanet Innovations <spinnovations.india@gmail.com>, October 2015
 */
package com.spi.uvschool.user.api;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import com.spi.uvschool.domain.Address;
import com.spi.uvschool.domain.AuthorizationToken;
import com.spi.uvschool.domain.SocialUser;
import com.spi.uvschool.domain.User;
import com.spi.uvschool.exception.ValidationException;

/**
 *
 * @author:
 */
@XmlRootElement
public class ExternalUser implements Principal {

	private String id;

	private boolean app;

	public boolean isApp() {
		return app;
	}

	public void setApp(boolean app) {
		this.app = app;
	}

	@Length(max = 50)
	private String firstName;
	@Length(max = 50)
	private String lastName;

	@Email(message = "{email.invalid}")
	private String emailAddress;

	private Long pkId;

	private boolean isVerified;

	private String role;

	private long userId;
	private String mobile;
	private String houseNo;
	private String address;
	private String city;
	private String state;
	private String pinCode;

	private String userImage;

	private long routeId;

	private List<SocialProfile> socialProfiles = new ArrayList<SocialProfile>();

	public ExternalUser() {
	}

	public ExternalUser(String userId) {
		this.id = userId;
	}

	public ExternalUser(User user) {
		this.userId = user.getId();
		this.id = user.getUuid().toString();
		this.emailAddress = user.getEmailAddress();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.isVerified = user.isVerified();
		for (SocialUser socialUser : user.getSocialUsers()) {
			SocialProfile profile = new SocialProfile();
			profile.setDisplayName(socialUser.getDisplayName());
			profile.setImageUrl(socialUser.getImageUrl());
			profile.setProfileUrl(socialUser.getProfileUrl());
			profile.setProvider(socialUser.getProviderId());
			profile.setProviderUserId(socialUser.getProviderUserId());
			socialProfiles.add(profile);
		}

		Address addressObj = user.getAddresses().get(0);
		this.address = addressObj.getAddressLine2();
		this.state = addressObj.getState();
		this.city = addressObj.getCity();
		this.houseNo = addressObj.getAddressLine1();
		this.pinCode = addressObj.getPinCode();
		this.mobile = user.getMobileNumber();
		this.routeId = user.getRouteId();

		this.userImage = user.getUserImage();

		role = user.getRole().toString();
		this.pkId = user.getId();
		if (user.getRole() != null) {
			this.role = user.getRole().name();
		}
	}

	public ExternalUser(User user, AuthorizationToken activeSession) {
		this(user);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public List<SocialProfile> getSocialProfiles() {
		return socialProfiles;
	}

	public String getId() {
		return id;
	}

	public boolean isVerified() {
		return isVerified;
	}

	public String getName() {
		return emailAddress;
	}

	public String getRole() {
		return role;
	}

	public Long getPkId() {
		return pkId;
	}

	public void setPkId(Long pkId) {
		this.pkId = pkId;
	}

	public String getMobile() {
		try {

			// Long.parseLong(this.mobile);
			return this.mobile;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ValidationException("Invalid Mobile Number.", "mobile");
		}
	}

	public void setMobile(String mobile) {
		// this.mobile = mobile;
		try {

			// Long.parseLong(this.mobile);
			this.mobile = mobile;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ValidationException("Invalid Mobile Number.", "mobile");
		}
	}

	public String getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pin) {
		this.pinCode = pin;
	}

	public long getRouteId() {
		return routeId;
	}

	public void setRouteId(long routeId) {
		this.routeId = routeId;
	}

	public String getUserImage() {
		return userImage;
	}

	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

}
