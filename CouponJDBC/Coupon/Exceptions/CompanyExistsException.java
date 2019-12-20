package Exceptions;

public class CompanyExistsException extends Exception{
	/**
	 * Throw if the company is already exists.
	 */
	private static final long serialVersionUID = 1L;

	public CompanyExistsException() {
		super("The typed Company exists already!");
	}
}
