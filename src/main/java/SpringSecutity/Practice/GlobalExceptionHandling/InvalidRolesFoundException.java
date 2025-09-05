package SpringSecutity.Practice.GlobalExceptionHandling;

@SuppressWarnings("serial")
public class InvalidRolesFoundException extends RuntimeException{

	public InvalidRolesFoundException(String msg) {
		super(msg);
	}
}
