package com.tevin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tevin.connectionutil.*;
import com.tevin.model.*;

public class ReimbDAOImpl implements ReimbDAO {

	public Reimbursements getRequest(Integer id) {
		Reimbursements r = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try (Connection conn = ConnectionUtil.getConnection()) {
			ps = conn.prepareStatement("SELECT * FROM ERsystem.reimbursements WHERE reimb_id = ?;");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				r = new Reimbursements (
						rs.getInt(1),
						rs.getInt(2),
						rs.getDouble(3),
						rs.getString(4),
						rs.getString(5)
						);
			}
		} catch (SQLException e) {
				e.printStackTrace();
				return null;
		}
		return r;
	}
	
	public Boolean newRequest(Reimbursements requests) {
		PreparedStatement ps = null;
		try (Connection conn = ConnectionUtil.getConnection()) {
			ps = conn.prepareStatement("INSERT INTO ERsystem.reimbursements VALUES (DEFAULT, ?, ?, ?, 'Pending');");
			ps.setInt(1, requests.getEmp_id());
			ps.setDouble(2, requests.getAmount());
			ps.setString(3, requests.getReason());
			
			ps.executeQuery();
			
		} catch (SQLException e) {
				e.printStackTrace();
				return false;
		}
		return true;
	}
	
	public Boolean newRequest(Employees employee, Double amount, String reason) {
		PreparedStatement ps = null;
		try (Connection conn = ConnectionUtil.getConnection()) {
			ps = conn.prepareStatement("INSERT INTO ERsystem.reimbursements VALUES (DEFAULT, ?, ?, ?, 'Pending');");
			ps.setInt(1, employee.getEmp_id());
			ps.setDouble(2, amount);
			ps.setString(3, reason);
			
			ps.executeQuery();
			
		} catch (SQLException e) {
				e.printStackTrace();
				return false;
		}
		return true;
	}
	
	public List<Reimbursements> allRequests() {
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Reimbursements> requests = new ArrayList<Reimbursements>();
		try (Connection conn = ConnectionUtil.getConnection()) {
			String query = "SELECT * FROM ERsystem.reimbursements;";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				Reimbursements r = new Reimbursements();
				r.setReimb_id(rs.getInt(1));
				r.setEmp_id(rs.getInt(2));
				r.setAmount(rs.getDouble(3));
				r.setReason(rs.getString(4));
				r.setStatus(rs.getString(5));
				
				requests.add(r);
			}
			
		} catch (SQLException e) {
				e.printStackTrace();
				return null;
		}
		return requests;
	}
	
	public List<Reimbursements> allPendingRequests() {
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Reimbursements> requests = new ArrayList<Reimbursements>();
		try (Connection conn = ConnectionUtil.getConnection()) {
			String query = "SELECT * FROM ERsystem.reimbursements WHERE status = 'Pending';";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				Reimbursements r = new Reimbursements();
				r.setReimb_id(rs.getInt(1));
				r.setEmp_id(rs.getInt(2));
				r.setAmount(rs.getDouble(3));
				r.setReason(rs.getString(4));
				r.setStatus(rs.getString(5));
				
				requests.add(r);
			}
			
		} catch (SQLException e) {
				e.printStackTrace();
				return null;
		}
		return requests;
	}
	
	public List<Reimbursements> allResolvedRequests() {
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Reimbursements> requests = new ArrayList<Reimbursements>();
		try (Connection conn = ConnectionUtil.getConnection()) {
			String query = "SELECT * FROM ERsystem.reimbursements WHERE status = 'Approve' OR status = 'Deny';";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				Reimbursements r = new Reimbursements();
				r.setReimb_id(rs.getInt(1));
				r.setEmp_id(rs.getInt(2));
				r.setAmount(rs.getDouble(3));
				r.setReason(rs.getString(4));
				r.setStatus(rs.getString(5));
				
				requests.add(r);
			}
			
		} catch (SQLException e) {
				e.printStackTrace();
				return null;
		}
		return requests;
	}
	
	public Boolean updateRequest(Reimbursements requests) {
		PreparedStatement ps = null;
		try (Connection conn = ConnectionUtil.getConnection()) {
			ps = conn.prepareStatement("UPDATE ERsystem.reimbursements SET status = ? WHERE reimb_id = ?;");
			ps.setString(1, requests.getStatus());
			ps.setInt(2, requests.getReimb_id());
			ps.executeUpdate();
			
		} catch (SQLException e) {
				e.printStackTrace();
				return false;
		}
		return true;
	}
	
	public Boolean removeRequest(Integer id) {
		PreparedStatement ps = null;
		try (Connection conn = ConnectionUtil.getConnection()) {
			String query = "DELETE FROM ERsystem.reimbursements WHERE emp_id=?;";
			ps = conn.prepareStatement(query);
			ps.setInt(1,id);
			
			if (ps.executeUpdate() == 0)
				return false;
			else
				return true;
		} catch (SQLException e) {
				e.printStackTrace();
				return false;
		}
	}
}
