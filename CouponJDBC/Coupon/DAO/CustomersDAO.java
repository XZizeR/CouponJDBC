package DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Beans.Customer;
import Exceptions.CustomerNotFoundException;

public interface CustomersDAO {

	// Checks whether the customer actually exists in the DB.
	boolean isCustomerExists(String email, String password) throws SQLException;
	int isCustomerExistsInt(String email, String password) throws SQLException;
	
	// Adds a new customer object to the DB;
	void addCustomer(Customer customer) throws SQLException, CustomerNotFoundException;

	// Updating one customer in the DB, by ID.
	void updateCustomer(Customer customer) throws SQLException;
	
	// Deleting one customer in the DB, by ID.
	void deleteCustomer(int customerID) throws CustomerNotFoundException, SQLException;

	List<Customer> getAllCustomers = new ArrayList<>();

	// Shows all customers from the DB.
	List<Customer> getAllCustomers() throws SQLException, CustomerNotFoundException;
	
	// Shows one customer from the DB, by ID. 
	Customer getOneCustomer(int customerID) throws SQLException;

}
