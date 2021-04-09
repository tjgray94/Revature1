package com.tevin.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tevin.dao.ReimbDAO;
import com.tevin.dao.ReimbDAOImpl;
import com.tevin.model.Reimbursements;

/**
 * Servlet implementation class AllResolved
 */
public class AllResolved extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AllResolved() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ReimbDAO rDAO = new ReimbDAOImpl();
		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();
		
		List<Reimbursements> all = rDAO.allResolvedRequests();
		
		for (Reimbursements r: all) {
			out.print("Reimbursement#: " + r.getReimb_id() + ", Employee ID: " + r.getEmp_id() + ", Amount: " 
						+ r.getAmount() + ", Reason: " + r.getReason() + ", Status: " + r.getStatus() +"<br>");
		}	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
