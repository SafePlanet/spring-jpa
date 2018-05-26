package com.spi.uvschool.vm;

import com.spi.uvschool.domain.EmailTemplate;

public class EmailTemplateVM {

	long id ;
	String name = null;
	String vmUrl = null;
	char userInitiated;
	
	public EmailTemplateVM(EmailTemplate emailTemplate){
		id = emailTemplate.getId();
		name = emailTemplate.getName();
		vmUrl = emailTemplate.getVmUrl();
		userInitiated = emailTemplate.getUserInitiated();
	}
	
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	
}
