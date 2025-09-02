package SpringSecutity.Practice.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/MethodBased/Auth")
public class MethodBasedAuthorizationController {
	
	
	@PreAuthorize("hasRole('CUSTOMER')")
	@GetMapping("/customer")
	public String M_Customer() {
		return "METHOD BASED AUTHIRAZATION FOR CUSTOMER";
	}
	
	
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/admin")
	public String M_Admin() {
		return "METHOD BASED AUTHIRAZATION FOR ADMIN";
	}
	
	
	
	@PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
	@GetMapping("/both")
	public String M_Both() {
		return "METHOD BASED AUTHIRAZATION FOR BOTH";
	}

}
