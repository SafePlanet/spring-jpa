package com.spi.uvschool.user.api;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

public class ChangePasswordRequest implements Serializable {
	
	private static final long serialVersionUID = -3650084821975704871L;
	
	@Length(min = 8, max = 30, message="{password.length}")
	String password;
	
	@Length(min = 8, max = 30, message="{password.length}")
	String cPassword;
	
	String oPassword;
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getcPassword() {
		return cPassword;
	}
	public void setcPassword(String cPassword) {
		this.cPassword = cPassword;
	}
	
	public boolean Validate(){
		return password.equals(cPassword);
	}
	public String getoPassword() {
		return oPassword;
	}
	public void setoPassword(String oPassword) {
		this.oPassword = oPassword;
	}
	
	

}
