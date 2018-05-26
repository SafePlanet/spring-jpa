/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spi.uvschool.exception;


public class InvalidRegIdException extends BaseWebApplicationException {

	private static final long serialVersionUID = -6909184325157564439L;
		String message;
	public InvalidRegIdException() {
                
		super(404, "40408", "Registration Id is not valid",
				"Registration Id is not valid");
                this.message="Registration Id is not valid";
	}
}