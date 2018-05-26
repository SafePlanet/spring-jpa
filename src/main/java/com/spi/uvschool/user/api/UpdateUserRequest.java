/* Copyright (C) SafePlanet Innovations, LLP - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by SafePlanet Innovations <spinnovations.india@gmail.com>, October 2015
 */
package com.spi.uvschool.user.api;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.Email;

import com.spi.uvschool.exception.ValidationException;

/**
 *
 * @version 1.0
 * @author:
 * 
 */
@XmlRootElement
public class UpdateUserRequest {

        
	private String firstName;
	private String lastName;
	
	private String mobile;
	private String houseNo;
	private String address;
	private String city;
	private String state;
	private String pinCode;	
	private String userImage;
	private String studentsList;

	@Email
	@NotNull
	private String emailAddress;

	public UpdateUserRequest() {
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

	public String getMobile() {
            try {
                
                //Long.parseLong(this.mobile);
		return this.mobile;
            }
            catch(Exception e)
            {
                e.printStackTrace();
                throw new ValidationException("Invalid Mobile Number.", "mobile");
            }
	}

	public void setMobile(String mobile) {
		//this.mobile = mobile;
                try {
                
                //Long.parseLong(this.mobile);
		this.mobile = mobile;
            }
            catch(Exception e)
            {
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

	public String getUserImage() {
		return userImage;
	}

	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}

	public String getStudentsList() {
		return studentsList;
	}

	public void setStudentsList(String studentsList) {
		this.studentsList = studentsList;
	}
	
}
