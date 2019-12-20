package Exceptions;

public class CouponStockException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CouponStockException() {
		super("Company was not found!");
	}
	
	
}
