/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spi.uvschool.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spi.uvschool.domain.EmailTemplate;

public interface EmailTemplateDAO extends JpaRepository<EmailTemplate, Long>{
    
	@Query("select et from EmailTemplate et where et.userInitiated = 'Y'")
	List<EmailTemplate> findUserInitiatedTemplated();
}
