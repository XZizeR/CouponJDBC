package Exceptions;

public class CouponExpiredException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CouponExpiredException() {
		super("Coupon date is expired");
	}
}
