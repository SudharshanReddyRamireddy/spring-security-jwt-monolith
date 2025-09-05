package SpringSecutity.Practice.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import SpringSecutity.Practice.DTOs.UserDetailsResponse;
import SpringSecutity.Practice.model.User;
import SpringSecutity.Practice.repository.UserRepository;

@Service
public class AdminServices {
	
	
	@Autowired
	private UserRepository userRepository;
	
	
	// fetch user by user id
	public UserDetailsResponse getUserById(Long userId) {
	    User existingUser =  userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("USER NOT FOUND WITH ID : " + userId));
	    
	    return UserDetailsResponse.builder()
	    									.id(existingUser.getId())
	    									.userName(existingUser.getUsername())
	    									.roles(existingUser.getRoles().stream().map(R -> R.getName()).toList())
	    									.build();
	}
	
	
	
	// fetch user by userName
	public UserDetailsResponse getUserByUserName(String userName) {
		User existingUser =  userRepository.findByUsername(userName).orElseThrow(() -> new UsernameNotFoundException("USER NOT FOUND WITH ID : " + userName));
	    
	    return UserDetailsResponse.builder()
	    									.id(existingUser.getId())
	    									.userName(existingUser.getUsername())
	    									.roles(existingUser.getRoles().stream().map(R -> R.getName()).toList())
	    									.build();
	}
	
	
	
	
	//Method Handling that Existing User Details Update
	//public 
	
	
}
