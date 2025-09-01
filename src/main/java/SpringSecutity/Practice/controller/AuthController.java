package SpringSecutity.Practice.controller;


import SpringSecutity.Practice.model.User;
import SpringSecutity.Practice.repository.UserRepository;
import SpringSecutity.Practice.service.JwtService;
import SpringSecutity.Practice.service.UserDetailsServiceImpl;

import java.util.Optional;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {

	@Autowired
    private UserRepository userRepo;
	
	@Autowired
    private PasswordEncoder encoder;
	
	@Autowired
    private AuthenticationManager authManager;
	
	@Autowired
    private JwtService jwt;
	
	@Autowired
    private UserDetailsServiceImpl uds;

	
	
	// Public: Register (role must be ADMIN/CUSTOMER/EDITOR, not with "ROLE"
	// prefix)
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody User req) throws BadRequestException {
		Optional<User> isUserNameExist = userRepo.findByUsername(req.getUsername());
		if (isUserNameExist.isPresent()) {
			throw new BadRequestException("ERROR : USERNAME " + req.getUsername() + " ALREADY EXISTS.");
		} else {
			req.setPassword(encoder.encode(req.getPassword()));
			userRepo.save(req);
			return ResponseEntity.status(HttpStatus.OK).body("USER SUCCESSFULLY REGISTRED.");
		}
    }
    
    
    

    // Public: Login -> returns JWT string
    @PostMapping("/login")
    public String login(@RequestBody User req) {
        authManager.authenticate(new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));
        UserDetails user = uds.loadUserByUsername(req.getUsername());
        return jwt.generateToken(user);
    }
    
}
