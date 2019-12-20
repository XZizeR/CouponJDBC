package DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import Beans.Coupon;


public interface CouponsDAO {
	
	void addCoupon(Coupon coupon) throws SQLException;
	void updateCoupon(Coupon coupon) throws SQLException;
	void deleteCoupon(int couponID) throws SQLException, Exceptions.CouponNotFoundException;
	ArrayList<Coupon> getAllCoupons() throws SQLException;
	Coupon getOneCoupon(int couponID) throws SQLException, Exceptions.CouponNotFoundException;
	void addCouponPurchase(int customerID, int couponID) throws SQLException;
	void deleteCouponPurchase(int customerID,int couponID) throws SQLException, Exceptions.PurchaseNotFoundException;
	
}
