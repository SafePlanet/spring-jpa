package com.spi.uvschool.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.spi.uvschool.domain.User;


public class PasswordGenerator {
	
	static String dbURL = "jdbc:mysql://localhost:3306/spiprod";
    static String username = "root";
    static String dbPassword = "Itrack";
    static String query = "";
//    static String passwordToSet = "Spipass1";
    static String passwordToSet = "password";
    Connection dbCon = null;
    PreparedStatement stmt = null;
    int startUserId = 5000;
    int endUserId = 5100;
	
	public static void main(String[] args) {
		PasswordGenerator generator = new PasswordGenerator();
		generator.generateAndSave();
		
		
	}
	
	private void generateAndSave(){
		Map<Long, User> userMap = getUsers(startUserId, endUserId);
		Iterator<Long> itr = userMap.keySet().iterator();
		while (itr.hasNext()){
			Long id = itr.next();
			User user = userMap.get(id);
			String password = user.getHashedPassword();
			savePassword(id, password);
			System.out.println("userId = " + id + ":::: Password = " + password);
		}
	}
	
	private void savePassword(long userId, String password){
		try {
		query = "UPDATE user SET hashed_password = '" + password + "' WHERE id = " +userId + ";";
        
			stmt = getConnection().prepareStatement(query);
			stmt.executeUpdate(query);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			
			try {
				getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private Map<Long, User> getUsers(long fromUserId, long toUserId){
		
		 ResultSet rs = null;
		 Map<Long, User> users = new HashMap<Long, User>();
		 
		 String query = "select id, uuid from  user where hashed_password = 'password' order by 1 ";
			try {
				PreparedStatement stmt = getConnection().prepareStatement(query);
//				stmt.setString(1, String.valueOf(fromUserId));
//				stmt.setString(2, String.valueOf(toUserId));
		        rs = stmt.executeQuery(query);
		        while (rs.next()) {
		        	User user = new User();
		        	user.setUuid(rs.getString(2));
		        	user.setHashedPassword(user.hashPassword(passwordToSet));
		        	users.put(rs.getLong(1), user);
		        }
		        
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				
				try {
					getConnection().close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		 
		 return users;
		
	}
	
	private Connection getConnection() throws SQLException{
		Connection connection = null;
		if(connection == null || connection.isClosed()){
			connection = DriverManager.getConnection(dbURL, username, dbPassword);
		}
		return connection;
	}

}
