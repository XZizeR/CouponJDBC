package Facade;

import java.sql.SQLException;
import java.util.ArrayList;

import Beans.Company;
import Beans.Coupon;
import Beans.Customer;
import DBDAO.CompaniesDBDAO;
import DBDAO.CustomersDBDAO;
import Exceptions.CompanyExistsException;
import Exceptions.CompanyNotFoundException;
import Exceptions.CustomerExistException;
import Exceptions.CustomerNotFoundException;
import Exceptions.LoginException;

public class AdminFacade extends ClientFacade {

	// Constructor
	public AdminFacade() {
		this.companiesDAO = new CompaniesDBDAO();
		this.customersDAO = new CustomersDBDAO();
	}

	// Login into the system as Admin
	@Override
	public int login(String email, String password) throws LoginException {
		if (email.toLowerCase().equals("admin@admin.com") && password.equals("admin")) {
			return 1;
		} else {
			throw new LoginException();
		}
	}

	// Add a Company
	public void addCompany(Company company) throws SQLException {
		ArrayList<Company> companies = getAllCompanies();
		boolean existsEmail = false; // to deal with email already exists
		try {
			for (Company comp : companies) {
				if (comp.getEmail().equalsIgnoreCase(company.getEmail())) {
					existsEmail = true;
					throw new CompanyExistsException();
				}
			}
			if (!existsEmail) {
				companiesDAO.addCompany(company);
				System.out.println("Company added successfully.");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// Update a Company
	public void updateCompany(Company company) throws SQLException {
		ArrayList<Company> companies = getAllCompanies();
		boolean existsEmail = false; // to deal with email exists
		boolean existsId = false; // to deal with id not found
		try {
			for (Company comp : companies) {
				if (comp.getEmail().equals(company.getEmail())) {
					existsEmail = true;
					throw new CompanyExistsException();
				}
				if (comp.getId() == company.getId())
					existsId = true;
			}
			if (!existsEmail && existsId) {
				companiesDAO.updateCompany(company);
				System.out.println("Company updated successfully.");
			} else
				throw new CompanyNotFoundException();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// Delete a Company
	public void deleteCompany(int companyID) throws SQLException {
		boolean existsID = false; // to deal with company not found.
		ArrayList<Company> companies = getAllCompanies();
		try {
			for (Company comp : companies) {
				if (comp.getId() == companyID) {
					existsID = true;
					ArrayList<Coupon> companyCoupons = companiesDAO.getOneCompany(companyID).getCoupons();
					for (Coupon coup : companyCoupons) {
						ArrayList<Customer> customers = customersDAO.getAllCustomers();
						for (Customer cust : customers) {
							couponsDAO.deleteCouponPurchase(cust.getId(), coup.getId());
						}
						couponsDAO.deleteCoupon(coup.getId());
					}
				}
			}
			if (!existsID)
				throw new CompanyNotFoundException();
			companiesDAO.deleteCompany(companyID);
			System.out.println("Company deleted successfully.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// Get all Companies
	public ArrayList<Company> getAllCompanies() throws SQLException {
		return companiesDAO.getAllCompanies();
	}

	// Get one Company
	public Company getOneCompany(int companyID) throws SQLException {
		return companiesDAO.getOneCompany(companyID);
	}

	// Add a Customer
	public void addCustomer(Customer customer) throws SQLException, CustomerNotFoundException {
		ArrayList<Customer> customers = getAllCustomers();
		boolean existsEmail = false; // to deal with email already exists
		try {
			for (Customer cust : customers) {
				if (cust.getEmail().equals(customer.getEmail())) {
					existsEmail = true;
					throw new CustomerExistException();
				}
			}
			if (!existsEmail) {
				customersDAO.addCustomer(customer);
				System.out.println("Customer added successfully.");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// Update a Customer
	public void updateCustomer(Customer customer) throws SQLException, CustomerNotFoundException {
		ArrayList<Customer> customers = getAllCustomers();
		boolean existsEmail = false; // to deal with email exists
		boolean existsId = false; // to deal with id not found
		try {
			for (Customer cust : customers) {
				if (cust.getEmail().equals(customer.getEmail())) {
					existsEmail = true;
					throw new CustomerExistException();
				}
				if (cust.getId() == customer.getId())
					existsId = true;
			}
			if (!existsEmail && existsId) {
				customersDAO.updateCustomer(customer);
				System.out.println("Customer updated successfully.");
			} else
				throw new CustomerNotFoundException();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// Delete a Customer
	public void deleteCustomer(int customerID) {
		boolean existsID = false; // to deal with customer not found.
		try {
			ArrayList<Customer> customers = customersDAO.getAllCustomers();
			for (Customer cust : customers) {
				if (cust.getId() == customerID) {
					existsID = true;
					ArrayList<Coupon> coupons = customersDAO.getOneCustomer(customerID).getCoupons();
					for (Coupon coup : coupons) {
						// deletes the coupons from the customer.
						couponsDAO.deleteCouponPurchase(customerID, coup.getId());
					}
				}
			}
			if (!existsID)
				throw new CustomerNotFoundException();
			// deletes the customer
			customersDAO.deleteCustomer(customerID);
			System.out.println("Customer deleted successfully!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// Get all Customers
	public ArrayList<Customer> getAllCustomers() throws SQLException {
		return customersDAO.getAllCustomers();
	}

	// Get one Customer
	public Customer getOneCustomer(int customerID) throws SQLException {
		return customersDAO.getOneCustomer(customerID);
	}

}
