package com.tevin.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tevin.dao.*;
import com.tevin.model.Reimbursements;

/**
 * Servlet implementation class SelectRequest
 */
public class SelectRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectRequest() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ReimbDAO rDAO = new ReimbDAOImpl();
		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();
		
		Integer reID = Integer.parseInt(request.getParameter("reID"));
		String status = request.getParameter("status");
		Reimbursements req = new Reimbursements(reID, 0, 0, "", status);
		Boolean result = rDAO.updateRequest(req);
		
		String whatever = "<head>\n"
				+ "<meta charset=\"UTF-8\">\n"
				+ "<title>Request Status</title>\n"
				+ "</head>\n"
				+ "<body>\n"
				+ "<h3>Request Status</h3>\n"
				+ "<div>\n"
				+ "<form action=\"select\" method=\"get\">\n"
				+ "Update status of a request using Reimbursement ID: <input type=\"number\" name=\"reID\">\n"
				+ "<br>\n"
				+ "Approve or Deny?\n"
				+ "<select name=\"status\">\n"
				+ "	<option value=\"Approved\">Approved</option>\n"
				+ "	<option value=\"Denied\">Denied</option>\n"
				+ "</select>\n"
				+ "<br>\n"
				+ "<input type=\"submit\" value=\"Submit\">\n"
				+ "</form>\n"
				+ "<br><br>\n"
				+ "<form action=\"ManagerPage.html\" method=\"get\">\n"
				+ "<input type=\"submit\" value=\"Back\">\n"
				+ "</form>\n"
				+ "Error Occurred Please Try Again";
		
				
	
		if (result != false){
			out.print("Request Updated");
		} else {
			out.print("Error Occurred Please Try Again");
			out.print(whatever);
	}
		out.print("</div>\n" + "</body>");
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
