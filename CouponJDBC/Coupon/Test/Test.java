package Test;

import java.io.ObjectInputStream.GetField;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import Beans.Category;
import Beans.Company;
import Beans.ConnectionPool;
import Beans.Coupon;
import Beans.Customer;
import DAO.CompaniesDAO;
import DBDAO.CompaniesDBDAO;
import Exceptions.CompanyExistsException;
import Exceptions.CompanyNotFoundException;
import Exceptions.CouponException;
import Exceptions.CouponExistException;
import Exceptions.CouponNotFoundException;
import Exceptions.CustomerExistException;
import Exceptions.CustomerNotFoundException;
import Exceptions.LoginException;
import Exceptions.PurchaseNotFoundException;
import Facade.AdminFacade;
import Facade.ClientFacade;
import Facade.CompanyFacade;
import Facade.CustomerFacade;
import Job.CouponExpirationDailyJob;
import Login.ClientType;
import Login.LoginManager;

public class Test {
	
	// Program
	public static void main(String[] args) {
		// multi-exception
		try {
			testAll();
			
		} catch (SQLException | CouponException | LoginException | CompanyNotFoundException | CouponNotFoundException
				| PurchaseNotFoundException | CustomerNotFoundException | CompanyExistsException | CustomerExistException | CouponExistException e) {
			e.printStackTrace();
		}
	}
	
	// Test
	public static void testAll() throws SQLException, CouponException, LoginException, CompanyNotFoundException,
			CouponNotFoundException, PurchaseNotFoundException, CustomerNotFoundException, CompanyExistsException, CustomerExistException, CouponExistException {

		CouponExpirationDailyJob job = new CouponExpirationDailyJob(); 
		// NOT working.
		// Don't know how to delete coupons of a customer.
		Thread t = new Thread(job);  
		t.start();  
		
		
		try {
		
		LoginManager logMan = LoginManager.getInstance();

		//***** Login *****//
		// choose your login account:
		AdminFacade admnfacade =  (AdminFacade) logMan.login("admin@admin.com", "admin", ClientType.Administrator);
//		CompanyFacade compfacade =  (CompanyFacade) logMan.login("coca@cola.com", "cO1a", ClientType.Company);
//		CustomerFacade custfacade =  (CustomerFacade) logMan.login("bar@gmail.com", "bar", ClientType.Customer);
		//*****************//
		
		
		//************************//
		//***** Admin Facade *****//
		//************************//
		// WELCOME. choose the method you want to use as Admin:
			
		// addCompany - company // works.
//		admnfacade.addCompany(new Company("Tesla", "elon.ceo@muskempire.com", "mar5"));
		
		// updateCompany - company // works.
//		admnfacade.updateCompany(new Company(9, "EL-AL", "elal.market@elal.gov.co.il", "fireMe"));
		
		// deleteCompany - company // works - try to delete company 1.
//		admnfacade.deleteCompany(19);
		
		// getAllCompanies - company // works.
		ArrayList<Company>companies = admnfacade.getAllCompanies();
		for (Company c : companies) {
			System.out.println(c);
		}
		
		// getOneCompanies - company // works.
//		System.out.println(admnfacade.getOneCompany(1));
		
		// addCustomer - customer // works.
//		admnfacade.addCustomer(new Customer("Bar", "Exception", "bar@yandex.com", "bar"));

		// updateCustomer - customer // works.
//		admnfacade.updateCustomer(new Customer(1, "Nick", "Blay", "nick@gmail.com", "nick"));

		// deleteCustomer - customer // works.
//		admnfacade.deleteCustomer(6);
		
		// getAllCustomers - customer // works.
//		ArrayList<Customer>customers = admnfacade.getAllCustomers();;
//		for (Customer c : customers) {
//			System.out.println(c);
//		}	
		
		// getOneCustomer - customer // works.
//		System.out.println(admnfacade.getOneCustomer(2));
		
		// customer - exception 
//		admnfacade.updateCustomer(new Customer(99, "Nick", "Blay", "nick@gmail.com", "nick")); // email - works.
//		admnfacade.updateCustomer(new Customer(99, "Nick", "Blay", "nicknew@gmail.com", "nick")); // id - works.		
		
		//**************************//
		//***** Company Facade *****//
		//**************************//
		// WELCOME. choose the method you want to use as Company:

		// addCoupon // works.
//		compfacade.addCoupon(new Coupon(8, 1500, Category.Electricity, "sodaStream", "Family Special", "soda-steam-machine46v.jpeg", new Date(119,4,14), new Date(120,4,14), 450));
		
		// updateCoupon // works.
//		compfacade.updateCoupon(new Coupon(5, 4, 1200, Category.Food, "Dr Paper special", "6 bottles special...", "dr-paper-6pack.jpeg", new Date(119,9,15), new Date(119,10,15), 25));
		
		// deleteCoupon // works.
//		compfacade.deleteCoupon(10);
		
		// getCompanyCoupons #1 // works.
//		ArrayList<Coupon>coupons = compfacade.getCompanyCoupons();
//		for (Coupon c : coupons) { 
//			System.out.println(c);
//		}
		
		// getCompanyCoupons #2 // works.
//		ArrayList<Coupon>coupons = compfacade.getCompanyCoupons(Category.Food);
//		for (Coupon c : coupons) { 
//			System.out.println(c);
//		}
		
		// getCompanyCoupons #3 // works.
//		ArrayList<Coupon>coupons = compfacade.getCompanyCoupons(30);
//		for (Coupon c : coupons) { 
//			System.out.println(c);
//		}
		
		// getCompanyDetails // works.
//		compfacade.getCompanyDetails();
		
		
		//***************************//
		//***** Customer Facade *****//
		//***************************//
		// WELCOME. choose the method you want to use as Customer:
		
		// purchaseCoupon // works.
//		custfacade.purchaseCoupon(new Coupon(8,7, 1500, Category.Electricity, "sodaStream", "Family Special", "soda-steam-machine46v.jpeg", new Date(119,4,14), new Date(120,4,14), 450));
//		custfacade.purchaseCoupon(new Coupon(1,1, 15, Category.Food, "Bamba Coupon", "3 bamba for 10 nis!", "Bamba.img", new Date(119,9,29), new Date(119,10,3), 5.5));

		// getCustomerCoupons #1 // works.
//		ArrayList<Coupon>coupons = custfacade.getCustomerCoupons();
//		for (Coupon c : coupons) {
//			System.out.println(c);
//		}
		
		// getCustomerCoupons #2 // works.
//		ArrayList<Coupon>coupons = custfacade.getCustomerCoupons(Category.Food);
//		for (Coupon c : coupons) {
//			System.out.println(c);
//		}
		
		// getCustomerCoupons #3 // works.
//		ArrayList<Coupon>coupons = custfacade.getCustomerCoupons(25);
//		for (Coupon c : coupons) {
//			System.out.println(c);
//		}
		
		// getCustomerDetails // works.
//		System.out.println(custfacade.getCustomerDetails());
		}catch(Exception e) {
			System.out.println(e);
		} finally {
			job.stop();
			ConnectionPool.getInstance().closeAllConnections();			
		}
	}
}