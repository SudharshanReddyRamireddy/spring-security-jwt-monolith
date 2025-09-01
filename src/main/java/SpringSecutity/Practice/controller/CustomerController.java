package SpringSecutity.Practice.controller;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    
	@GetMapping("/") // This API can able to access Only Customer
    public String profile() { return "Customer Controller"; }
}
