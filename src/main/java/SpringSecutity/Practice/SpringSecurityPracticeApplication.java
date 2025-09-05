package SpringSecutity.Practice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class SpringSecurityPracticeApplication {
	
	private static final Logger log = LoggerFactory.getLogger(SpringSecurityPracticeApplication.class);

	
	public static void main(String[] args) {
		log.info("************************ APLICATION STARTED ********************************");
		SpringApplication.run(SpringSecurityPracticeApplication.class, args);
	}

}
