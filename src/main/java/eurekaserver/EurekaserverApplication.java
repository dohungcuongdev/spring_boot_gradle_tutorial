package eurekaserver;

/*
 * Eureka Server is an application that holds the information about all client-service applications. 
 * Every Micro service will register into the Eureka server and Eureka server knows all the 
 * client applications running on each port and IP address. 
 * Eureka Server is also known as Discovery Server.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaserverApplication { // Eureka Server

   // Make sure Eureka Server configuration in application.properties file is effective
   // Comment all Service Registration with Eureka configuration in application.properties file
   // test Eureka Server - run this class with server port 8761 - go to http://localhost:8761/
   public static void main(String[] args) {
      SpringApplication.run(EurekaserverApplication.class, args);
   }
}