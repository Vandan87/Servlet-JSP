package com.registration.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.registration.dao.EmployeeDao;
import com.registration.model.Employee;

/**
 * This is a Java servlet that handles employee registration. It listens for
 * HTTP GET and POST requests on the URL "/register". The servlet interacts with
 * an EmployeeDao to register new employees in a database.
 * 
 * @author VANDAN
 */
@WebServlet("/register")
public class EmployeeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private EmployeeDao employeeDao;
	public void init() {
		employeeDao = new EmployeeDao();
	}

	// Handling HTTP GET requests
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		// Sending a response to the client
		response.getWriter().append("Served at: ").append(request.getContextPath());
		// Forwarding the request to the "employeeregister.jsp"
		RequestDispatcher dispatcher = request.getRequestDispatcher("EmployeeRegister.jsp");
		dispatcher.forward(request, response);
	}

	// Handling HTTP POST requests
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		// Retrieving from parameters from the request
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String address = request.getParameter("address");
		String contact = request.getParameter("contact");
		String email = request.getParameter("email");

		// Creating an employee instance and set attributes
		Employee employee = new Employee();
		employee.setFirstName(firstName);
		employee.setLastName(lastName);
		employee.setUserName(userName);
		employee.setPassword(password);
		employee.setAddress(address);
		employee.setContact(contact);
		employee.setEmail(email);
		boolean contactExists = false;
		try {
			contactExists = employeeDao.checkContactExists(contact);
			if (contactExists) {
				request.setAttribute("error", "Contact number already exists");
				request.setAttribute("firstName", firstName);
				request.setAttribute("lastName", lastName);
				request.setAttribute("userName", userName);
				request.setAttribute("password", password);
				request.setAttribute("address", address);
				request.setAttribute("email", email);
				RequestDispatcher dispatcher = request.getRequestDispatcher("EmployeeRegister.jsp");
				dispatcher.forward(request, response);
				return;
			} else {
				employeeDao.registerEmployee(employee);
				request.setAttribute("userName", userName);
				// Forwarding the request to the "employeedetails.jsp"
				RequestDispatcher dispatcher = request.getRequestDispatcher("EmployeeDetails.jsp");
				dispatcher.forward(request, response);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}