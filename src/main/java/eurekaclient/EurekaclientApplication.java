package eurekaclient;

/*
 * How to register the Spring Boot Micro service application into the Eureka Server. 
 * Before registering the application, please make sure Eureka Server is running 
 * on the port 8761 or first build the Eureka Server and run it.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient // Service Registration with Eureka
@RestController
public class EurekaclientApplication {

	// test Service Registration with Eureka
	// Make sure Service Registration with Eureka configuration in application.properties file is effective
	// Comment all Eureka Server configuration in application.properties file
	// run EurekaserverApplication class with server port 8761 - go to http://localhost:8761/
	// run this class with port 9090 - go to http://localhost:9090/
	// check the Instances currently registered with Eureka at http://localhost:8761/
	public static void main(String[] args) {
		SpringApplication.run(EurekaclientApplication.class, args);
	}

	@RequestMapping(value = "/")
	public String home() {
		return "Eureka Client application";
	}
}