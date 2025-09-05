package SpringSecutity.Practice.DTOs;



import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Login_Request {
	
	private String userName;
	
	private String password;

}
