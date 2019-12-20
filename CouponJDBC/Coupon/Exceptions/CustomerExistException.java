package Exceptions;

public class CustomerExistException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomerExistException() {
		super("The typed Customer exists already!");
	}
}
