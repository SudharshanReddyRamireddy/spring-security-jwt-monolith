package SpringSecutity.Practice.DTOs;

import java.util.Set;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Register_Request {
	
	
	private String username;
    private String password;
    private Set<String> roles; // just names
    
}
