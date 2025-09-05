package SpringSecutity.Practice.GlobalExceptionHandling;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandling {
	
	// Method for Return ResponseBody with clearly;
	private static ResponseEntity<Map<String, Object>> buildResponse(String message, HttpStatus status) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message", message);
        return new ResponseEntity<>(body, status);
    }
	
	
	//Method for Handle for UserNotFoundException exception
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Map<String, Object>> UserNotFoundExceptionHandler(UserNotFoundException ex){
		return buildResponse(ex.getMessage(), HttpStatus.UNAUTHORIZED);
	}
		
	
	
	
	//Method for Handle for UserNotFoundException exception
	@ExceptionHandler(TokenExpiredException.class)
	public ResponseEntity<Map<String, Object>> UserNotFoundExceptionHandler(TokenExpiredException ex){
		return buildResponse(ex.getMessage(), HttpStatus.UNAUTHORIZED);
	}
	
	//UserAlreadyExistException Handler
	@ExceptionHandler(UserAlreadyExistException.class)
	public ResponseEntity<Map<String, Object>> UserAlreadyExistExceptionHandiling(UserAlreadyExistException ex){
		return buildResponse(ex.getMessage(),HttpStatus.CONFLICT);
	}
	
	
	//Method for Handle for Invalid Roles exception
	@ExceptionHandler(InvalidRolesFoundException.class)
	public ResponseEntity<Map<String, Object>> InvalidRoleExceptionHandler(InvalidRolesFoundException ex){
		return buildResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	
	
	//Method for Handle for BadCrdls exception
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<Map<String, Object>> otherExceptionHandler(BadCredentialsException ex){
		return buildResponse(ex.getMessage(), HttpStatus.UNAUTHORIZED);
	}
	
	
	
	
	//Method for Handle for other all Runtime Exceptions
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<Map<String, Object>> otherExceptionHandler(RuntimeException ex){
		return buildResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	
	
	//Method for Handle for other all Exceptions
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, Object>> otherExceptionHandler(Exception ex){
		return buildResponse(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
