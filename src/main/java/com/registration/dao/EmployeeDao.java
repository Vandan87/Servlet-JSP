package com.registration.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.registration.model.Employee;

/**
 * This class handles database operations related to Employee registration.
 * 
 * @author VANDAN
 */
public class EmployeeDao {

	// Connaction method
	private Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/employees", "root", "Vandan@123");
	}

	public int registerEmployee(Employee employee) throws ClassNotFoundException {
		String employeeData = "Insert into employee"
							+ " (first_name,last_name,username,password,address,contact,email) " 
							+ "VALUES" 
							+ "(?,?,?,?,?,?,?)";
		int result = 0;
		// Create a statement using connection object
		try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(employeeData)) {
			preparedStatement.setString(1, employee.getFirstName());
			preparedStatement.setString(2, employee.getLastName());
			preparedStatement.setString(3, employee.getUserName());
			preparedStatement.setString(4, employee.getPassword());
			preparedStatement.setString(5, employee.getAddress());
			preparedStatement.setString(6, employee.getContact());
			preparedStatement.setString(7, employee.getEmail());
			System.out.println(preparedStatement);
			// Execute the query or update the query
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return result;
	}

	// Check if a contact already exists in a database
	public boolean checkContactExists(String contact) throws ClassNotFoundException {
		String contactExists = "SELECT COUNT(*) FROM employee WHERE contact = ?";
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(contactExists)) {
			preparedStatement.setString(1, contact);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					int count = resultSet.getInt(1);
					return count > 0;
				}
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return false;
	}

}