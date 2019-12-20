package Login;


import java.sql.SQLException;

import Exceptions.LoginException;
import Facade.AdminFacade;
import Facade.ClientFacade;
import Facade.CompanyFacade;
import Facade.CustomerFacade;

public class LoginManager {

	//	private static LoginManager instance = new LoginManager();

	private static LoginManager instance;
	
	private LoginManager() {
		
	}
	
	public static LoginManager getInstance() {
		if(instance == null)
			instance = new LoginManager();
		return instance;
	}
	
	// Login
	public ClientFacade login(String email, String password, ClientType clientType) throws LoginException, SQLException {
		
		switch(clientType) {
		case Administrator:
			AdminFacade admin = new AdminFacade();
			if(admin.login(email, password)>0)
				return admin;
			else
				throw new LoginException();
		case Customer:
			CustomerFacade customer = new CustomerFacade();
			if(customer.login(email, password)>0)
				return customer;
			else
				throw new LoginException();
		case Company:
			CompanyFacade company = new CompanyFacade();
			if(company.login(email, password)>0)
				return company;
			else
				throw new LoginException();
		}
		
		return null;
		
	}
	
	
}
