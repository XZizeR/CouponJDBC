package Facade;

import java.sql.SQLException;

import DBDAO.CompaniesDBDAO;
import DBDAO.CouponsDBDAO;
import DBDAO.CustomersDBDAO;
import Exceptions.LoginException;

public abstract class ClientFacade {
	protected CompaniesDBDAO companiesDAO = new CompaniesDBDAO();
	protected CouponsDBDAO couponsDAO = new CouponsDBDAO();
	protected CustomersDBDAO customersDAO = new CustomersDBDAO();

	public abstract int login(String email, String password) throws SQLException, LoginException;
}
