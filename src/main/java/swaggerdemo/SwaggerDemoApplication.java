package swaggerdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/*
 * Swagger2 is an open source project used to generate the REST API documents for RESTful web services. 
 * It provides a user interface to access our RESTful web services via the web browser.
 */

@SpringBootApplication
@EnableSwagger2
public class SwaggerDemoApplication { //Enabling Swagger2
   public static void main(String[] args) {
	  // test on http://localhost:9090/swagger-ui.html
      SpringApplication.run(SwaggerDemoApplication.class, args);
   }
   
   @Bean
   public Docket productApi() {
      return new Docket(DocumentationType.SWAGGER_2).select()
         .apis(RequestHandlerSelectors.basePackage("swaggerdemo")).build();
   }
}