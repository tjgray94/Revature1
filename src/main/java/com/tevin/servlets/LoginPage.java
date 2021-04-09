package com.tevin.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tevin.dao.*;
import com.tevin.model.Employees;

/**
 * Servlet implementation class LoginPage
 */
public class LoginPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static {
	    try {
	      Class.forName("org.postgresql.Driver");
	    } catch (ClassNotFoundException e) {
	      System.err.println("PostgreSQL DataSource unable to load PostgreSQL JDBC Driver");
	    }
	  } 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginPage() {
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	EmployeeDAOImpl eDAO = new EmployeeDAOImpl();
		
		HttpSession session = request.getSession();
		
		response.setContentType("text/html");  
	    PrintWriter out = response.getWriter();  
	    
	    String username = request.getParameter("username");
	    String password = request.getParameter("pass_word");
	    
	    System.out.println(username + password);
	    session.setAttribute("employee", username);
	    
	    Employees emp = new Employees();  
	    emp = eDAO.getEmpByLogin(username, password);
	   
	    session.setAttribute("currentuser", emp);
	    String title = emp.getTitle();
	    
	    if (emp != null && title.equals("Manager")) {
	    	RequestDispatcher rd = request.getRequestDispatcher("/ManagerPage.html");
	    	rd.forward(request, response);
	    	
	    } else if (emp != null && title.equals("Employee")) {
	    	RequestDispatcher rd = request.getRequestDispatcher("/EmployeePage.html");
	    	rd.forward(request, response);
	    	
	    } else {
	    	out.print("<div style='color:red;'>Incorrect Username or Password</div>"); 
	    	RequestDispatcher rd = request.getRequestDispatcher("/Login.html");
	 	    rd.forward(request, response);
	    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
