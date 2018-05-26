/**
 * 
 */
package com.spi.uvschool.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Safeplanet Innovations
 *
 */
public class DuplicateUUIFinder {

	String dbURL = "jdbc:mysql://localhost:3306/spiprod?autoReconnect=true";
	String username = "root";
	String password = "admin";
	Connection dbCon = null;
	List<String> allTables = new ArrayList<String>();

	public static void main(String[] args) {

		DuplicateUUIFinder uuiFinder = new DuplicateUUIFinder();
		uuiFinder.createConnection();
		uuiFinder.populateAllTables();
		uuiFinder.showDuplicateRecords();

	}

	private void populateAllTables() {
		String findTablesSQL = "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES "
				+ "WHERE TABLE_TYPE = 'BASE TABLE' AND TABLE_SCHEMA='spiprod'";
		try {
			Statement statement = dbCon.createStatement();
			ResultSet rs = statement.executeQuery(findTablesSQL);
			while (rs.next()) {
				allTables.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void showDuplicateRecords() {
		for (String table : allTables) {
			findDuplicateRecordsForTable(table);
		}

	}

	private void findDuplicateRecordsForTable(String table) {
		List<String> uuids = new ArrayList<String>();
		String findUuid = "Select uuid from " + table;
		try {
			Statement statement = dbCon.createStatement();
			ResultSet rs = statement.executeQuery(findUuid);
			while (rs.next()) {
				uuids.add(rs.getString(1));
			}
			// } catch(MySQLSyntaxErrorException mse){
			// System.out.println("Problem in retriving UUID for table " + table);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Set<String> duplicateRecords = findDuplicates(uuids);
		if (!duplicateRecords.isEmpty())
			System.out.println("Duplicate UUID found on the table " + table + " Records " + duplicateRecords);
	}

	public static Set<String> findDuplicates(List<String> listContainingDuplicates) {

		final Set<String> duplicateRecords = new HashSet<String>();
		final Set<String> set1 = new HashSet<String>();

		for (String uuid : listContainingDuplicates) {
			if (!set1.add(uuid)) {
				duplicateRecords.add(uuid);
			}
		}
		return duplicateRecords;
	}

	private Connection createConnection() {

		try {
			if (dbCon == null)
				dbCon = DriverManager.getConnection(dbURL, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dbCon;
	}

}
