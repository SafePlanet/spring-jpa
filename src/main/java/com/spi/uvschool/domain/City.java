package com.spi.uvschool.domain;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.jpa.domain.AbstractPersistable;
@Entity
@Table(name = "city")
public class City extends AbstractPersistable<Long>{

private static final long serialVersionUID = 5263145567458714737L;


@ManyToOne
@JoinColumn(name = "state_id" ,referencedColumnName="id")
private State state;



private String cityName;



public State getState() {
	return state;
}



public void setState(State state) {
	this.state = state;
}



public String getCityName() {
	return cityName;
}



public void setCityName(String cityName) {
	this.cityName = cityName;
}










}
