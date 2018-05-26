package com.spi.uvschool.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spi.uvschool.config.ApplicationConfig;
import com.spi.uvschool.dao.StatesDAO;
import com.spi.uvschool.domain.City;
import com.spi.uvschool.domain.State;
import com.spi.uvschool.service.BaseService;
import com.spi.uvschool.service.StatesService;
import com.spi.uvschool.vm.StateCityVM;

@Service("statesService")
@Transactional
public class StatesServiceImpl extends BaseService implements StatesService {

	private static final Logger LOG = LoggerFactory.getLogger(StatesServiceImpl.class);

	private StatesDAO statesRepository;

	private ApplicationConfig applicationConfig;

	public StatesServiceImpl(Validator validator) {
		super(validator);
	}

	@Autowired
	public StatesServiceImpl(Validator validator, ApplicationConfig applicationConfig) {
		this(validator);
		this.applicationConfig = applicationConfig;
	}

	@Autowired
	public void setStatesRepository(StatesDAO statesRepository) {
		this.statesRepository = statesRepository;
	}

	@Override
	public List<StateCityVM> getStatesList() {
		List<StateCityVM> uservm= new ArrayList<StateCityVM>(); 
		List<State> states = statesRepository.getStates();
		for (State state :states){
			StateCityVM vm= new StateCityVM();
			vm.setStateId(state.getId());
			vm.setStateName(state.getStateName());
			uservm.add(vm);
		
		}
		return uservm ;
	}
	@Override
	public List<StateCityVM> getCityList(long stateId){
		List<StateCityVM> uservm= new ArrayList<StateCityVM>();
		List<City> cities=statesRepository.getCity(stateId);
		
		for( City city : cities){
			StateCityVM vm = new StateCityVM();
			vm.setCityId(city.getId());
			vm.setCityName(city.getCityName());
			uservm.add(vm);
			
		}
		return uservm;
	}

@Override
public State getStateById(long stateId){
	
	return statesRepository.getStateById(stateId);
}
}
