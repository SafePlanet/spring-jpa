package com.spi.uvschool.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spi.uvschool.domain.City;
import com.spi.uvschool.domain.State;

@Repository
public interface StatesDAO extends JpaRepository<State, Long> {

	@Query("select s from State s")
	List<State> getStates();
	
	@Query("select c from City c ,State s where c.state.id=s.id and s.id=?")
	List<City> getCity(long stateId);
	
	@Query("select s from State s Where s.id=?")
	State getStateById(long stateId);
}
