/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spi.uvschool.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.spi.uvschool.model.BaseEntity;

@Entity
@Table(name = "state")
public class State extends BaseEntity {
    
	private static final long serialVersionUID = -7060699604485881798L;
	private String stateName;

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }
    
    @OneToMany(mappedBy = "state", targetEntity = City.class, cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.TRUE)
	List<City> city;
    
    
}
