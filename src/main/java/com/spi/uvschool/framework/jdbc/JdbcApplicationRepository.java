package com.spi.uvschool.framework.jdbc;

public class JdbcApplicationRepository {
	
	private JdbcApplicationRepository repository = null;
	
	private JdbcApplicationRepository(){}
	
	public JdbcApplicationRepository getInstance(){
		if(repository == null){
			repository = new JdbcApplicationRepository();
		}
		return repository;
	}

	public void isStudentAbsentOnDate() {
		
//		QueryBuilder.create(selectPosition)
//        .setLong("deviceId", new Long(deviceId))
//        .setDate("from", startTime)
//        .setDate("to", endTime)
//        .executeQuery(Position.class);

	}
}
