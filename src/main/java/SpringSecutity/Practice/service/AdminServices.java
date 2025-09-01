package SpringSecutity.Practice.service;

import java.util.NoSuchElementException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SpringSecutity.Practice.model.User;
import SpringSecutity.Practice.repository.UserRepository;

@Service
public class AdminServices {
	
	
	@Autowired
	private UserRepository userRepository;
	
	
	// fetch user by user id
	public User getUserById(Long userId) {
	    return userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("USER NOT FOUND "));
	}
	
	
	
	// fetch user by username
	public User getUserByUserName(String userName) {
		return userRepository.findByUsername(userName).orElseThrow(() -> new NoSuchElementException("USER NOT FOUND"));
	}

}
