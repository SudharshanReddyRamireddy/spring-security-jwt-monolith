package SpringSecutity.Practice.controller;



import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import SpringSecutity.Practice.model.User;
import SpringSecutity.Practice.service.AdminServices;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
	
	
	@Autowired
	private AdminServices adminServices;
	
    @GetMapping("/") // THIS API CAN ABLE TO ACCES ONLY ADMIN
    public String dashboard() { return "Admin Controller"; }
    
    
    
    
    
    @GetMapping("/user/{userId}") // THIS API CAN ABLE TO ACCES ONLY ADMIN
    public ResponseEntity<User> getUserById(@PathVariable("userId") Long userId) throws BadRequestException{
    	try {
			return ResponseEntity.status(HttpStatus.OK).body(adminServices.getUserById(userId));
		} catch (Exception e) {
			throw new BadRequestException("ERROR : " + e.getMessage());
		}
    }
    
    
    
    
    @GetMapping("/user/{userName}") // THIS API CAN ABLE TO ACCES ONLY ADMIN
    public ResponseEntity<User> getUserByuserName(@PathVariable("userName") String userName) throws BadRequestException{
    	try {
			return ResponseEntity.status(HttpStatus.OK).body(adminServices.getUserByUserName(userName));
		} catch (Exception e) {
			throw new BadRequestException("ERROR : " + e.getMessage());
		}
    }
    
    
    
}
