package com.spi.uvschool.vm;

import com.spi.uvschool.domain.MessageTemplate;

public class MessageTemplateVM {

	long id ;
	String name = null;
	String sampleMessage = null;
	char userInitiated;
	
	public MessageTemplateVM(MessageTemplate messageTemplate){
		id = messageTemplate.getId();
		name = messageTemplate.getName();
		sampleMessage = messageTemplate.getSampleMessage();
		userInitiated = messageTemplate.getUserInitiated();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getSampleMessage() {
		return sampleMessage;
	}

	public void setSampleMessage(String sampleMessage) {
		this.sampleMessage = sampleMessage;
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
