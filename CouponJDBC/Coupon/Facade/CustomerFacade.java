package Facade;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import Beans.Category;
import Beans.Coupon;
import Beans.Customer;
import DBDAO.CouponsDBDAO;
import DBDAO.CustomersDBDAO;
import Exceptions.CouponException;
import Exceptions.CouponExistException;
import Exceptions.CouponExpiredException;
import Exceptions.CouponStockException;

public class CustomerFacade extends ClientFacade {

	// Field
	private int customerID;

	// Constructor
	public CustomerFacade() {
		this.couponsDAO = new CouponsDBDAO();
		this.customersDAO = new CustomersDBDAO();
	}

	public CustomerFacade(int customerID) {
		super();
		this.customerID = customerID;
	}

	// Login into a system as Customer
	@Override
	public int login(String email, String password) throws SQLException {
		int ID = customersDAO.isCustomerExistsInt(email, password);
		if (ID > 0) {
			this.customerID = ID;
			return customerID;
		}
		return -1;
	}

	// Purchase a Coupon // help
	public void purchaseCoupon(Coupon coupon) throws CouponException, SQLException, CouponExistException {
		ArrayList<Coupon> customerCoupons = getCustomerCoupons();
		try {
			for (Coupon coup : customerCoupons) {
				// check the amount
				if (coupon.getAmount() < 1) {
					throw new CouponStockException();
				}
				// check the date
				if (new Date().before(coupon.getEndDate())) {
					throw new CouponExpiredException();
				}
				// check if already purchased by the customer, if not - will purchase.
				if (coup.getId() == coupon.getId())
					throw new CouponExistException();
			}
			couponsDAO.addCouponPurchase(this.customerID, coupon.getId());
			coupon.setAmount(coupon.getAmount() - 1);
			couponsDAO.updateCoupon(coupon);
			System.out.println("Purchased added successfully.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	// Get Customer Coupons #1 - gives all customer coupons.
	public ArrayList<Coupon> getCustomerCoupons() throws SQLException {
		ArrayList<Coupon> customerCoupons = customersDAO.getOneCustomer(customerID).getCoupons();
		return customerCoupons;
	}

	// Get Customer Coupons #2 - give all customer coupons by category // help
	public ArrayList<Coupon> getCustomerCoupons(Category category) throws SQLException {
		ArrayList<Coupon> customerCoupons = customersDAO.getOneCustomer(customerID).getCoupons();
		ArrayList<Coupon> coups = new ArrayList<>();
		for (Coupon coupon : customerCoupons) {
			if (coupon.getCategory().equals(category)) {
				coups.add(coupon);
			}
		}
		return coups;
	}

	// Get Customer Coupons - give all customer coupons by max price #3
	public ArrayList<Coupon> getCustomerCoupons(double maxPrice) throws SQLException {
		ArrayList<Coupon> customerCoupons = customersDAO.getOneCustomer(customerID).getCoupons();
		ArrayList<Coupon> coups = new ArrayList<>();
		for (Coupon coupon : customerCoupons)
			if (coupon.getPrice() <= maxPrice)
				coups.add(coupon);
		return coups;
	}

	// getCustomerDetails
	public Customer getCustomerDetails() throws SQLException {
		return customersDAO.getOneCustomer(customerID);
	}

}