package com.tevin.servlets;

import java.io.IOException;
//import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tevin.dao.*;
import com.tevin.model.*;

/**
 * Servlet implementation class ManagerPage
 */
public class UpdateProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public EmployeeDAO eDAO = new EmployeeDAOImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateProfile() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	HttpSession session = request.getSession();
		
		response.setContentType("text/html");  
		//PrintWriter out = response.getWriter();
		
		String pass = request.getParameter("newpass");
		String userName = (String)session.getAttribute("username");
		Employees employee = eDAO.getEmployee(userName);
		Employees employee2 = (Employees) session.getAttribute("currentuser");
		eDAO.updateEmployee(employee2, pass);
		RequestDispatcher rd = request.getRequestDispatcher("viewprofile");
    	rd.forward(request, response);
    	
    }
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
		
	}

}
