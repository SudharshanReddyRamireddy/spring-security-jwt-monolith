package SpringSecutity.Practice.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/common")  // This Controller method does not have any authorization
public class CommonController {

	
	@GetMapping("/") // can able to access both CUSTOMER & ADMIN;
	public String wishes() {
		return "Hi.. Hello, This is From Common Controller";
	}
}
