package SpringSecutity.Practice.GlobalExceptionHandling;


@SuppressWarnings("serial")
public class TokenExpiredException extends RuntimeException{

		public TokenExpiredException(String msg) {
			super(msg);
		}
}
