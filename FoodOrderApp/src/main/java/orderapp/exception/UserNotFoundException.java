package orderapp.exception;

public class UserNotFoundException extends RuntimeException{
	
	private String message;
	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String msg) {
	super(msg);
	}
	
	@Override
	public String getMessage() {
		return this.message;
	}
	
}
