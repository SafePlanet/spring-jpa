/* Copyright (C) SafePlanet Innovations, LLP - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by SafePlanet Innovations <spinnovations.india@gmail.com>, October 2015
 */
package com.spi.uvschool.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.spi.uvschool.model.BaseEntity;
import com.spi.uvschool.vm.AddressVM;

@Entity
@Table(name = "address")
public class Address extends BaseEntity {

	private static final long serialVersionUID = -4111446789774090962L;

	// private static final int DEFAULT_EXPIRY_TIME_IN_MINS = 60 * 24; // 24 hours

	@Enumerated(EnumType.STRING)
	private String addressType;

	@ManyToOne
	@JoinColumn(name = "user_id")
	User user;

	private String addressLine1 = null;
	private String addressLine2 = null;
	private String city = null;
	private long cityId = 0;
	private String state = null;
	private String pinCode = null;
	private String country = null;
	private char voidInd;

	public Address() {
		super();

	}

	public Address(AddressVM addressVM, User user) {
		this();
		this.user = user;
		this.addressLine1 = addressVM.getAddressLine1();
		this.addressLine2 = addressVM.getAddressLine2();
		this.city = addressVM.getCity();
		this.state = addressVM.getState();
		this.pinCode = addressVM.getPinCode();

	}

	public Address(AddressVM addressVM) {
		this.addressLine1 = addressVM.getAddressLine1();
		this.addressLine2 = addressVM.getAddressLine2();
		this.city = addressVM.getCity();
		this.state = addressVM.getState();
		this.pinCode = addressVM.getPinCode();

	}

	/**
	 * @return the addressLine1
	 */
	public String getAddressLine1() {
		return addressLine1;
	}

	/**
	 * @param addressLine1
	 *            the addressLine1 to set
	 */
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	/**
	 * @return the addressLine2
	 */
	public String getAddressLine2() {
		return addressLine2;
	}

	/**
	 * @param addressLine2
	 *            the addressLine2 to set
	 */
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the pinCode
	 */
	public String getPinCode() {
		return pinCode;
	}

	/**
	 * @param pinCode
	 *            the pinCode to set
	 */
	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country
	 *            the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the addressType
	 */
	public String getAddressType() {
		return addressType;
	}

	/**
	 * @param addressType
	 *            the addressType to set
	 */
	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	/**
	 * @return the voidInd
	 */
	public char getVoidInd() {
		return voidInd;
	}

	public void setVoidInd(char voidInd) {
		this.voidInd = voidInd;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public long getCityId() {
		return cityId;
	}

	public void setCityId(long cityId) {
		this.cityId = cityId;
	}

}
