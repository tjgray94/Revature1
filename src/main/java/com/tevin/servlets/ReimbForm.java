package com.tevin.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tevin.dao.*;
import com.tevin.model.*;;

/**
 * Servlet implementation class ReimbForm
 */
public class ReimbForm extends HttpServlet {
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
    public ReimbForm() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ReimbDAO rDAO = new ReimbDAOImpl();
		response.setContentType("text/html");  
	    PrintWriter out = response.getWriter();  
	    
		int empID = Integer.parseInt(request.getParameter("empID"));
		Double amount = Double.parseDouble(request.getParameter("amount"));
		String reason = request.getParameter("reason");

		Reimbursements ri = new Reimbursements(1, empID, amount, reason, "Pending");
	    rDAO.newRequest(ri);
	    System.out.println("I work");
	    if (rDAO.newRequest (ri) != false) {
	    	out.print("Request Received!");
	    } else {
	    out.print("Error Occurred, Please Try Again");
	    }
}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
