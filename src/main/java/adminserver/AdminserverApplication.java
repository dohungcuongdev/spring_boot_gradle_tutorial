package adminserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import de.codecentric.boot.admin.config.EnableAdminServer;

/*
 * Monitoring your application by using Spring Boot Actuator Endpoint is slightly difficult. 
 * Because, if you have 'n' number of applications, every application has separate actuator endpoints,
 * thus making monitoring difficult. Spring Boot Admin Server is an application used to manage 
 * and monitor your Microservice application.
 *
 * To handle such situations, CodeCentric Team provides a Spring Boot Admin UI to manage and 
 *	monitor all your Spring Boot application Actuator endpoints at one place.
 */

@SpringBootApplication
@EnableAdminServer
public class AdminserverApplication { //Admin Server
   public static void main(String[] args) { 
	  // uncomment all related dependency in build.gradle, conflict with spring cloud dependency, need to comment or remove this
      SpringApplication.run(AdminserverApplication.class, args);
   }
}