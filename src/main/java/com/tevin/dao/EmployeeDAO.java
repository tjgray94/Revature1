package com.tevin.dao;

//import java.sql.SQLException;
import java.util.List;

import com.tevin.model.*;

public interface EmployeeDAO {
	
	public Employees getEmpByLogin(String username, String pass);
	public Employees getManByLogin(String username, String pass);
	//public Employees verifyEmp(Integer id, String pass) throws SQLException;
	public String getTitle(Integer id, String name);
	public Employees getEmployee(String username);
	public Boolean newEmployee(Employees emp);
	public List<Employees> allEmployees();
	public Boolean removeEmployee(Integer id);
	public Employees updateEmployee(Employees emp, String pass);
	
}
