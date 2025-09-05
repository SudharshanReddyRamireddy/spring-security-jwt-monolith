package SpringSecutity.Practice.controller;


import SpringSecutity.Practice.DTOs.Login_Request;
import SpringSecutity.Practice.DTOs.Register_Request;

import SpringSecutity.Practice.service.AuthServices;
import SpringSecutity.Practice.service.TokenBlockListService;
import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {
	
	private static final Logger log = LoggerFactory.getLogger(AuthController.class);
	
	
	@Autowired
	private AuthServices regestration_login_Services;
	
	@Autowired
	private TokenBlockListService TokenBlockListService;
	
	

	
	
	// Public: Register (role must be ADMIN/CUSTOMER/EDITOR, not with "ROLE"
	@PostMapping("/register")
	public ResponseEntity<Map<String, Object>> register(@RequestBody Register_Request req) {
		
		return buildResponse(regestration_login_Services.registerNewUser(req), HttpStatus.OK);
		
	}




    
  
    

    // Public: Login -> returns JWT string
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Login_Request req) {
        
    	Map<String, String> response = new HashMap<String, String>();
    	response.put("TOKEN", regestration_login_Services.userLogin(req));
    	response.put("STATUS", HttpStatus.OK.toString());
    	response.put("MESSAGE", "Login Success.");
    	return ResponseEntity.status(HttpStatus.OK).body(response);
    	
    }
    
    
    
    
    
    
    //static method which is build response with clear details
    @ResponseStatus(code = HttpStatus.OK)
	private static ResponseEntity<Map<String, Object>> buildResponse(String message, HttpStatus status) {
        
    	Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("message", message);
        return new ResponseEntity<>(body, status);
        
    }
    
    
    
    
    //
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            TokenBlockListService.blacklistToken(token);
            log.info("TOKEN IS ADDED TO BLOCKLIST:" + token);
        }
        return ResponseEntity.ok("Logged out successfully");
    }
}
