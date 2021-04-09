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
import com.tevin.model.Reimbursements;

/**
 * Servlet implementation class SubmitRequest
 */
public class SubmitRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubmitRequest() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ReimbDAOImpl rDAO = new ReimbDAOImpl();
		EmployeeDAOImpl eDAO = new EmployeeDAOImpl();
		
		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		
		String name = (String) session.getAttribute("employee");
		
		Employees employee = eDAO.getEmployee(name);
		Integer id = employee.getEmp_id();
		
		Double amount = Double.parseDouble(request.getParameter("amount"));
		String reason = request.getParameter("reason");
		
		rDAO.newRequest(employee, amount, reason);
		
		RequestDispatcher rd = request.getRequestDispatcher("allpending");
    	rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
