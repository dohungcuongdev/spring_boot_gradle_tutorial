package oauth2appjwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableAuthorizationServer
@EnableResourceServer
@RestController
public class WebsecurityappApplication {
	
	/*Step to test:
	 * uncomment all related config in application.properties
     * uncomment all related dependency in build.gradle, need spring cloud dependency
     * 
	 * Generate private key: Open terminal and run the command
	 * openssl genrsa -out jwt.pem 2048
	 * openssl rsa -in jwt.pem 
	 * 
	 * Generate public key: run the command
	 * openssl rsa -in jwt.pem -pubout
	 * 
	 * Change configuration in OAuth2Config file
	 * 
	 * Run this class WebsecurityappApplication to startup tomcat server
	 * 
	 * Open POSTMAN
	 * Method: POST
	 * URL: http://localhost:9090/oauth/token
	 * 
	 * Authorization: 
	 * Type: Basic Auth
	 * username: tutorialspoint
	 * password: my-secret-key
	 *
	 * Body
	 * grant_type: password
	 * username; Cuong
	 * password: password
	 * 
	 * -> Send
	 * 
	 * Copy access_token from response
	 * use token to access link in browser http://localhost:9090/products?access_token=.....
	 * 
	 * or use POSTMAN
	 * http://localhost:9090/products
	 * Header:
	 * Bearer .....
	 * 
	 * note: 
	 * the code in package oauth2appjwt may conflict 
	 * when run other @SpringBootApplication classes
	 * solution: if you run other @SpringBootApplication classes outside this package
	 * use default user:
	 * username: user
	 * password: (it will be generated in startup log)
	 * 
	*/
	public static void main(String[] args) {
		SpringApplication.run(WebsecurityappApplication.class, args);
	}

	@RequestMapping(value = "/products")
	public String getProductName() {
		return "Honey";
	}
}