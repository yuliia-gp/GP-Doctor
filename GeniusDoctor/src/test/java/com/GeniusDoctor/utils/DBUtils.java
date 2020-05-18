package com.GeniusDoctor.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DBUtils {

	private static Connection conn;
	private static Statement st;
	private static ResultSet rSet;
	private static List<Map<String,String>> listData;
	
	/**
	 * This method will create a new connection to DB
	 */
	public static void createConnection(){
		
		try {
			conn = DriverManager.getConnection(ConfigsReader.getProperty("dbUrl"), 
					ConfigsReader.getProperty("dbUsername"), ConfigsReader.getProperty("dbPassword"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * This method will retrieve data from DB by specified query, store inside a List of Maps and return it
	 * @return List<Map<String,String>>
	 */
	public static List<Map<String,String>> storeDataFromDB(String sqlQuery){
		try {
			st = conn.createStatement();
			rSet = st.executeQuery(sqlQuery);
			ResultSetMetaData rSetMD = rSet.getMetaData();
			listData = new ArrayList<>();
		
			while(rSet.next()) {
				Map<String, String> rowMap = new LinkedHashMap<>();
				for(int i = 1; i <= rSetMD.getColumnCount(); i++) {
					rowMap.put(rSetMD.getColumnName(i), rSet.getObject(i).toString());
				}
				listData.add(rowMap);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listData;			
	}
	
	
	/**
	 * This method will close database objects at the end of the use.
	 */
	public static void closeDBObjects() {
		try {	
			if(rSet!=null) {
				rSet.close();
			}
			if(st!=null) {
				st.close();
			}
			if(conn!=null) {
			conn.close();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
}
