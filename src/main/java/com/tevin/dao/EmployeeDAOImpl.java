package com.tevin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tevin.connectionutil.ConnectionUtil;
import com.tevin.model.Employees;

public class EmployeeDAOImpl implements EmployeeDAO {
	
	@Override
	public Employees getEmpByLogin(String username, String pass) {
		Employees emp = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			ps = conn.prepareStatement("SELECT * FROM ERsystem.employees WHERE username=? AND pass_word=?");
			ps.setString(1, username);
			ps.setString(2, pass);
	
			rs = ps.executeQuery();
			
			while (rs.next()) {
				emp = new Employees(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6)
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return emp;
	}
	
	public Employees getManByLogin(String username, String pass) {
		Employees emp = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			ps = conn.prepareStatement("SELECT * FROM ERsystem.employees WHERE username=? AND pass_word=? AND title='Manager';");
			ps.setString(1, username);
			ps.setString(2, pass);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				emp = new Employees(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6)
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return emp;
	}
	
	/* public Employees verifyEmp(Integer id, String pass) throws SQLException {
		Employees emp = null;
		String query = "SELECT * FROM ERsystem.employees WHERE emp_id = ?;";
		try (Connection conn = ConnectionUtil.getConnection()) {
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		
		if (rs.next()) {
			if (pass.equals(rs.getString(("pass_word")))) {
				int empID = rs.getInt("emp_id");
				String title = rs.getString("emp_title");
				String fName = rs.getString("fName");
				String lName = rs.getString("lName");
				
				emp = new Employees (empID, title, fName, lName);
			} else
				emp = new Employees();
		}
		return emp;
		}
	} */
	
	public String getTitle(Integer id, String name) {
		String emp = null; 
		ResultSet rs = null;
		PreparedStatement ps = null;
		try (Connection conn = ConnectionUtil.getConnection()) {
			ps = conn.prepareStatement("SELECT emp_title FROM ERsystem.employees WHERE emp_id = ? AND lName = ?;");
			ps.setInt(1, id);
			ps.setString(2, name);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				emp = rs.getString(1);
			}
		} catch (SQLException e) {
				e.printStackTrace();
				return null;
		}
			return emp;
	}
	
	public Employees getEmployee(String username) {
		Employees emp = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try (Connection conn = ConnectionUtil.getConnection()) {
			ps = conn.prepareStatement("SELECT * FROM ERsystem.employees WHERE username = ?;");
			ps.setString(1, username);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				emp = new Employees(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6)
						);
			}
		} catch (SQLException e) {
				e.printStackTrace();
				return null;
		}
		return emp;
	}
	
	public Boolean newEmployee(Employees emp) {
		PreparedStatement ps = null;
		try (Connection conn = ConnectionUtil.getConnection()) {
			ps = conn.prepareStatement("INSERT INTO ERsystem.employees VALUES (?,?,?,?,?,?);");
			ps.setInt(1, emp.getEmp_id());
			ps.setString(2, emp.getfName());
			ps.setString(3, emp.getlName());
			ps.setString(4, emp.getUsername());
			ps.setString(5, emp.getPass_word());
			ps.setString(6, emp.getTitle());
			
			ps.executeQuery();
			
		} catch (SQLException e) {
				e.printStackTrace();
				return false;
		}
		return true;
	}
	
	public List<Employees> allEmployees(){
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Employees> employees = new ArrayList<Employees>();
		try(Connection conn = ConnectionUtil.getConnection()) {
			String query = "SELECT * FROM ERsystem.employees;";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				Employees emp = new Employees();
				emp.setEmp_id(rs.getInt(1));
				emp.setfName(rs.getString(2));
				emp.setlName(rs.getString(3));
				emp.setUsername(rs.getString(4));
				emp.setPass_word(rs.getString(5));
				emp.setTitle(rs.getString(6));
				
				employees.add(emp);
			}
			
		} catch (SQLException e) {
				e.printStackTrace();
				return null;
		}
		return employees;
	}
	
	public Boolean removeEmployee(Integer id) {
		PreparedStatement ps = null;
		try (Connection conn = ConnectionUtil.getConnection()) {
			String query = "DELETE FROM ERsystem.employees WHERE emp_id=?;";
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			
			if (ps.executeUpdate() == 0)
					return false;
			else 
					return true;
			} 
		 catch (SQLException e) {
			 	e.printStackTrace();
				return false;
		 }
	}
	
	@Override
	public Employees updateEmployee(Employees emp, String pass) {
		//Employees emp = null;
		PreparedStatement ps;
		try (Connection conn = ConnectionUtil.getConnection()) {
		ps = conn.prepareStatement("UPDATE ERsystem.employees SET pass_word=? WHERE username = ?");
		ps.setString(1, pass);
		ps.setString(2, emp.getUsername());
		int res = ps.executeUpdate();
		
		if (res == 0) {
			return null;
		} else {
			return emp;
		}
			
		
		
		} catch (SQLException e) {
				e.printStackTrace();
				return null;
		}
		//return true;
	}

}
