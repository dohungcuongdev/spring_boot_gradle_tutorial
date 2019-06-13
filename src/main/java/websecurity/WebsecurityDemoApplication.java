package websecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebsecurityDemoApplication { //Securing Web Applications
	
	/* Test
     * uncomment all related dependency in build.gradle, need spring cloud dependency
	 * 
	 * Run this class WebsecurityDemoApplication
	 * 
	 * http://localhost:9090/ then click link
	 * or go directly to http://localhost:9090/login
	 * 
	 * username: user
	 * password: password
	 *  
	 */
	
	// working with login.html - hello.html and home.html in src/main/resources/templates/
	public static void main(String[] args) {
		SpringApplication.run(WebsecurityDemoApplication.class, args);
	}
}