package Exceptions;

public class PurchaseNotFoundException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PurchaseNotFoundException() {
		super("Purchase was not found!");
	}
}
