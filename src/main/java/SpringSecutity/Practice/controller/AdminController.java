package SpringSecutity.Practice.controller;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import SpringSecutity.Practice.DTOs.User_Response_Body;
import SpringSecutity.Practice.service.AdminServices;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
	
	
	@Autowired
	private AdminServices adminServices;
	
    @GetMapping("/") // THIS API CAN ABLE TO ACCES ONLY ADMIN
    public String dashboard() { return "Admin Controller"; }
    
    
    
    
    
    // Method to handle for fetched User by used id
    @GetMapping("/userId/{userId}") // THIS API CAN ABLE TO ACCES ONLY ADMIN
    public ResponseEntity<User_Response_Body> getUserById(@PathVariable("userId") Long userId){
    	
    	User_Response_Body response = User_Response_Body.builder()
    									.status(HttpStatus.OK)
    									.message("USER DETAILS FETCHED SECCUSSFULLY")
    									.body(adminServices.getUserById(userId))
    									.build();
    	
    	return ResponseEntity.ok(response);
    }
    
    
    
    
    
    // the method which is handle to fetch user by username
    @GetMapping("/userName/{userName}") // THIS API CAN ABLE TO ACCES ONLY ADMIN
    public ResponseEntity<User_Response_Body> getUserByuserName(@PathVariable("userName") String userName){
    	User_Response_Body response = User_Response_Body.builder()
				.status(HttpStatus.OK)
				.message("USER DETAILS FETCHED SECCUSSFULLY")
				.body(adminServices.getUserByUserName(userName))
				.build();
    	return ResponseEntity.ok(response);
    }
    
    
    //Update Existing User Details
    
}
