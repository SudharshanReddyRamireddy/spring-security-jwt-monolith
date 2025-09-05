package SpringSecutity.Practice.GlobalExceptionHandling;

@SuppressWarnings("serial") 
public class UserAlreadyExistException extends RuntimeException{
	public UserAlreadyExistException(String Msg) {
		super(Msg);
	}
}
