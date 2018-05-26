package com.spi.uvschool.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spi.uvschool.dao.EmailTemplateDAO;
import com.spi.uvschool.domain.EmailTemplate;
import com.spi.uvschool.service.BaseService;
import com.spi.uvschool.service.CommonService;
import com.spi.uvschool.service.UserService;
import com.spi.uvschool.vm.EmailTemplateVM;

@Service("commonService")
public class CommonServiceImpl extends BaseService implements CommonService {
	
	private static Logger LOG = LoggerFactory.getLogger(CommonServiceImpl.class);

	@Autowired
	public EmailTemplateDAO emailTemplateDAO;
	
	@Autowired
	public UserService userService;

	public CommonServiceImpl(Validator validator, EmailTemplateDAO emailTemplateDAO) {
		super(validator);
		this.emailTemplateDAO = emailTemplateDAO;
	}

	@Autowired
	public CommonServiceImpl(Validator validator) {
		super(validator);
	}

	public List<EmailTemplateVM> getAllEmailTemplates() {
		List<EmailTemplate> emailTemplates = emailTemplateDAO.findUserInitiatedTemplated();

		List<EmailTemplateVM> emailTemplateVms = new ArrayList<EmailTemplateVM>();
		for (EmailTemplate emailTemplate : emailTemplates) {
			emailTemplateVms.add(new EmailTemplateVM(emailTemplate));
		}
		return emailTemplateVms;
	}
	

}