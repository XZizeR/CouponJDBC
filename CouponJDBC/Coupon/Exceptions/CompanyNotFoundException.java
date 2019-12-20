package Exceptions;

public class CompanyNotFoundException extends Exception {
	/**
	 *  
	 */
	private static final long serialVersionUID = 1L;

	public CompanyNotFoundException() {
		super("Company was not found!");
	}
}
