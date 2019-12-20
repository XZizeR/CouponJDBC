package Facade;

import java.sql.SQLException;
import java.util.ArrayList;

import Beans.Category;
import Beans.Company;
import Beans.Coupon;
import DBDAO.CompaniesDBDAO;
import DBDAO.CouponsDBDAO;
import Exceptions.CouponExistException;
import Exceptions.CouponNotFoundException;


public class CompanyFacade extends ClientFacade {

	// Field
	private int companyID;

	// Constructor
	public CompanyFacade() {
		this.companiesDAO = new CompaniesDBDAO();
		this.couponsDAO = new CouponsDBDAO();
	}

	public CompanyFacade(int companyID) {
		super();
		this.companyID = companyID;
	}

	// Login into the system as Company and return Id
	@Override
	public int login(String email, String password) throws SQLException {
		int ID = companiesDAO.isCompanyExistsInt(email, password);
		if (ID > 0) {
			this.companyID = ID;
			return companyID;
		}
		return -1;
	}

	// Add one Coupon
	public void addCoupon(Coupon coupon) throws SQLException, CouponExistException {
		ArrayList<Coupon> coupons = couponsDAO.getAllCoupons();
		try {
			for (Coupon coup : coupons) {
				if (coupon.getCompanyID() == coup.getCompanyID()) {
					if (coup.getTitle().equalsIgnoreCase(coupon.getTitle())) {
						throw new CouponExistException();
					}
				}
			}
			couponsDAO.addCoupon(coupon);
			System.out.println("Coupon added successfully.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// Update one Coupon
	public void updateCoupon(Coupon coupon) throws SQLException, CouponNotFoundException {
		ArrayList<Coupon> coupons = couponsDAO.getAllCoupons();
		boolean existsID = false;
		try {
			for (Coupon coup : coupons) {
				// if the title of each coupon at the company is different.
				if (coupon.getCompanyID() == coup.getCompanyID()) {
					if (coup.getTitle().equalsIgnoreCase(coupon.getTitle())) {
						throw new CouponExistException();
					}
				}
				// if you found the coupon id.
				if (coup.getId() == coupon.getId())
					existsID = true;
			}
			if (existsID) {
				couponsDAO.updateCoupon(coupon);
				System.out.println("Coupon updated successfully.");
			} else
				throw new CouponNotFoundException();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// Delete one Coupon
	public void deleteCoupon(int couponID) {
		boolean existsID = false; // to deal with coupon not found.
		try {
			existsID = false;
			ArrayList<Coupon> coupons = couponsDAO.getAllCoupons();
			for (Coupon coup : coupons) {
				if (coup.getId() == couponID) {
					existsID = true;
				}
			}
			if (!existsID)
				throw new CouponNotFoundException();
			couponsDAO.deleteCoupon(couponID);
			System.out.println("Coupon deleted successfully.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// getCompanyCoupons #1 - all coupons of the company
	public ArrayList<Coupon> getCompanyCoupons() throws SQLException {
		ArrayList<Coupon> coupons = couponsDAO.getAllCoupons();
		ArrayList<Coupon> coups = new ArrayList<>();
		for (Coupon coupon : coupons) {
			if (companyID == coupon.getCompanyID())
				coups.add(coupon);
		}
		return coups;
	}

	// getCompanyCoupons #2 - by category
	public ArrayList<Coupon> getCompanyCoupons(Category category) throws SQLException {
		ArrayList<Coupon> coupons = couponsDAO.getAllCoupons();
		ArrayList<Coupon> coups = new ArrayList<>();
		for (Coupon coupon : coupons) {
			if (companyID == coupon.getCompanyID())
				if (coupon.getCategory().equals(category))
					coups.add(coupon);
		}
		return coups;
	}

	// getCompanyCoupons #3 - by max price
	public ArrayList<Coupon> getCompanyCoupons(double maxPrice) throws SQLException {
		ArrayList<Coupon> coupons = couponsDAO.getAllCoupons();
		ArrayList<Coupon> coups = new ArrayList<>();
		for (Coupon coupon : coupons) {
			if (companyID == coupon.getCompanyID())
				if (coupon.getPrice() <= maxPrice)
					coups.add(coupon);
		}
		return coups;
	}

	// getCompanyDetails
	public Company getCompanyDetails() throws SQLException {
		return companiesDAO.getOneCompany(companyID);
	}

}
