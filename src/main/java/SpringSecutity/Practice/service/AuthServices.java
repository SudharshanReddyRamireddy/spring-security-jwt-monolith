package SpringSecutity.Practice.service;

import java.util.HashSet;
import java.util.Set;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import SpringSecutity.Practice.DTOs.Login_Request;
import SpringSecutity.Practice.DTOs.Register_Request;
import SpringSecutity.Practice.GlobalExceptionHandling.InvalidRolesFoundException;
import SpringSecutity.Practice.GlobalExceptionHandling.UserAlreadyExistException;
import SpringSecutity.Practice.model.Role;
import SpringSecutity.Practice.model.User;
import SpringSecutity.Practice.repository.RoleRepository;
import SpringSecutity.Practice.repository.UserRepository;

@Service
public class AuthServices {
	
	private UserRepository userRepo;
	private PasswordEncoder encoder;
	private RoleRepository roleRepo;
    private AuthenticationManager authManager;
    private JwtService jwt;
    private UserDetailsServiceImpl userDetailsServiceIMP;
	
	public AuthServices(UserRepository userRepo,PasswordEncoder encoder, RoleRepository roleRepo, AuthenticationManager authManager, JwtService jwt, UserDetailsServiceImpl userDetailsServiceIMP) {
		this.userRepo = userRepo;
		this.encoder = encoder;
		this.roleRepo = roleRepo;
		this.authManager = authManager;
		this.jwt =  jwt;
		this.userDetailsServiceIMP = userDetailsServiceIMP;
	}
	
	
	
	
	// Method which Handles to New User Registration
	public String registerNewUser(Register_Request req) {
		
		 // Check if user exists
	    if (userRepo.findByUsername(req.getUsername()).isPresent()) {
	        throw new UserAlreadyExistException("USER ALREADY EXIST WITH USERNAME : " + req.getUsername());
	    }

	    // Encode password
	    String encodedPassword = encoder.encode(req.getPassword());  
	    
	   // checking Requested Roles are Exists or not, if any invalid roles collect all and thrown an exception
	    boolean isRolesInvalid = false;
	    StringBuilder invalidRoles = new StringBuilder();
	    Set<Role> rolesSet = new HashSet<Role>();
	    
	    for(String reqRole  : req.getRoles()) {
		   if(roleRepo.findByName(reqRole).isPresent()) {
			  rolesSet.add(roleRepo.findByName(reqRole).get());
		   }else {
			   isRolesInvalid = true;
			   invalidRoles.append(reqRole + ",");
		   }
	   }
	    
	   if(isRolesInvalid) {
		   throw new InvalidRolesFoundException("INVALID ROLES : " + invalidRoles);
	   }
 
	   
	    // Create User entity
	    User user = User.builder()
	    			.username(req.getUsername())
	    			.password(encodedPassword)
	    			.roles(rolesSet)
	    			.build();

	    // Save User entity
	    userRepo.save(user);
	    return "User successfully registered";
	}
	
	
	
	
	
	
	//Method which handles Login-service
	public String userLogin(Login_Request req) {
		authManager.authenticate(new UsernamePasswordAuthenticationToken(req.getUserName(), req.getPassword()));
        UserDetails user = userDetailsServiceIMP.loadUserByUsername(req.getUserName());
        return jwt.generateToken(user);
	}

}
