/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spi.uvschool.user.api;

import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author Gungun&Goldy
 */

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class ComplaintMessageRequest  {

	@Length(max = 1024, message = "{message.text.exceeded}")
	private String message = null;
	private long userId;

	public ComplaintMessageRequest() {
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

}
