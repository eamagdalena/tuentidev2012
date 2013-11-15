package tuenti.p17;

public class InvalidPizzaException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public InvalidPizzaException(String message) {
		super(message);
	}
}
