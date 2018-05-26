package com.spi.uvschool.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.spi.uvschool.domain.State;
import com.spi.uvschool.vm.StateCityVM;

@Transactional
public interface StatesService {

	public List<StateCityVM> getStatesList();  
	public List<StateCityVM> getCityList(long stateId);
    public State getStateById(long stateId);
	
}
