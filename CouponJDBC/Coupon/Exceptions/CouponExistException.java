package Exceptions;

public class CouponExistException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CouponExistException() {
		super("The typed Coupon exists already!");
	}
}
