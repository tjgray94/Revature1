package com.tevin.connectionutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.postgresql.Driver;

import com.tevin.dao.*;
import com.tevin.model.*;

public class ConnectionUtil {
	
public static Connection getConnection() throws SQLException {
		
		Driver PostgresDriver = new Driver();          
		DriverManager.registerDriver(PostgresDriver);
		String url = "jdbc:postgresql://localhost:5433/postgres";
		String user = "postgres";
		String pass = "postgres";
		return DriverManager.getConnection(url, user, pass);
	}
	static EmployeeDAOImpl eDAO = new EmployeeDAOImpl();
	
	public static void main(String[] args) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			Employees test = eDAO.getEmployee("bgates1"); 
			System.out.println(test);
			System.out.println("The connection was successful!");
		} catch (SQLException e) {
				System.out.println("An error Occured");
				e.printStackTrace();
		}
	}
}
