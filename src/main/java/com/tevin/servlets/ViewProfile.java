package com.tevin.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tevin.dao.*;
import com.tevin.model.Employees;

/**
 * Servlet implementation class EmployeePage
 */
public class ViewProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
    public ViewProfile() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		EmployeeDAOImpl eDAO = new EmployeeDAOImpl();
		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();
		
		String name = (String) session.getAttribute("employee");
		
		Employees emp = eDAO.getEmployee(name);
		
		Integer empID = emp.getEmp_id();
		String fName = emp.getfName();
		String lName = emp.getlName();
		String username = emp.getUsername();
		String title = emp.getTitle();
		
		out.print("<h2>Profile Info</h2>" + "<p><u>Employee ID: </u></p>" + empID + "<br>" + "<p><u>Name: </u></p>" + fName 
				+ " " + lName + "<br>" + "<p><u>Username: </u></p>" + username + "<br>" + "<p><u>Title: </u></p>" + title);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

