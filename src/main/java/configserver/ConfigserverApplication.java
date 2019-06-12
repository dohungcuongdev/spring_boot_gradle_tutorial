package configserver;

/*
 * Spring Cloud Configuration Server is a centralized application that manages all the 
 * application related configuration properties. 
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer //Cloud Configuration Server
public class ConfigserverApplication {

/*
 * Configuration Server runs on the Tomcat port 8888 and application configuration properties are loaded from native search locations..
 */
	
   // Make sure Configuration Server in application.properties file is effective
   // comment all Cloud Configuration Client in application.properties and run this class with port 8888 
   // create a file config-client.properties in folder D:/Spring-Boot/demo/configprop/
   // go to http://localhost:8888/config-client/default/master
   public static void main(String[] args) {
      SpringApplication.run(ConfigserverApplication.class, args);
   }
}