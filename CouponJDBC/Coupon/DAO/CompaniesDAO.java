package DAO;

import java.sql.SQLException;
import java.util.ArrayList;

import Beans.Company;
import Exceptions.CompanyNotFoundException;

public interface CompaniesDAO {

	// Checks whether the company actually exists in the DB.
	boolean isCompanyExists(String email, String password) throws SQLException; // return true/false
	int isCompanyExistsInt(String email, String password) throws SQLException; // return id number

	
	// Add a new company object to the DB;
	void addCompany(Company company) throws SQLException, CompanyNotFoundException;

	// Updating one company in the DB, by ID.
	void updateCompany(Company company) throws SQLException;

	// Deleting one company in the DB, by ID.
	void deleteCompany(int companyID) throws SQLException, CompanyNotFoundException;

	ArrayList<Company> getAllCompanies = new ArrayList<>();

	// Shows all companies from the DB.
	ArrayList<Company> getAllCompanies() throws SQLException;

	// Shows one company from the DB, by ID. 
	Company getOneCompany(int companyID) throws SQLException;


}
