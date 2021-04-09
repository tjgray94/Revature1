package com.tevin.servlets;

import java.io.IOException;
//import java.io.PrintWriter;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tevin.dao.*;
import com.tevin.model.Employees;


/**
 * Servlet implementation class ViewEmployees
 */
public class ViewEmployees extends HttpServlet {
	private static final long serialVersionUID = 1L;
	       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewEmployees() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EmployeeDAO eDAO = new EmployeeDAOImpl();
		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();
		
		List<Employees> emp = eDAO.allEmployees();
		
		/*String something = "<head>\n"
				+ "\n"
				+ "    <meta charset=\"utf-8\">\n"
				+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">\n"
				+ "    <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">\n"
				+ "    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js\"></script>\n"
				+ "    <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js\"></script>\n"
				+ "\n"
				+ "    </head>\n"
				+ "<h3 style=\"color:orange\"> Welcome 'name'!</h3>\n"
				+ "    <body>\n"
				+ "        <br>\n"
				+ "        <p style=\"color:white\">What would you like to do?</p>\n"
				+ "        <form action=\"viewemployees\" method=\"get\">\n"
				+ "        	<input type=\"submit\" value=\"View All Employees\">\n"
				+ "        </form>\n"
				+ "        <br><br>\n"
				+ "      	<form action=\"allpending\" method=\"get\">\n"
				+ "        	<input type=\"submit\" value=\"Pending Requests\">\n"
				+ "        </form>\n"
				+ "        <br><br>\n"
				+ "        <form action=\"allresolved\" method=\"get\">\n"
				+ "        	<input type=\"submit\" value=\"Resolved Requests\">\n"
				+ "        </form>\n"
				+ "        <br><br><br>\n"
				+ "        <form action=\"logout\" method=\"post\" class=\"btn btn-outline-primary btn-lg pull-right\">\n"
				+ "        	<input type=\"submit\" value=\"Logout\">\n"
				+ "        </form>\n";
		
		out.print(something);
		*/
		for (Employees e: emp) {
			out.print("Employee ID: " + e.getEmp_id() + ", Name: " + e.getfName() + " " 
						+ e.getlName() + ", Title: " + e.getTitle() +"<br>");
		}	
		
		//out.print("</body>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
