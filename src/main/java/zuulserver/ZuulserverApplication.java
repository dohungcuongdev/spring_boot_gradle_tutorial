package zuulserver;

/*
 * Zuul Server is a gateway application that handles all the requests and does the 
 * dynamic routing of microservice applications. The Zuul Server is also known as Edge Server.
 * For Example, /api/user is mapped to the user service and /api/products is mapped to the 
 * product service and Zuul Server dynamically routes the requests to the respective backend 
 * application.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class ZuulserverApplication {
   // uncomment all related config in application.properties
   // uncomment all related dependency in build.gradle, need spring cloud dependency
   // refresh gradle
   // run Application.java with port 9091 - http://localhost:9091/api/products
   // then run ZuulserverApplication with port 9090
   // access http://localhost:9090/api/products and API is mapped from ...9091.. to ...9090...
   public static void main(String[] args) {
      SpringApplication.run(ZuulserverApplication.class, args);
   }
}