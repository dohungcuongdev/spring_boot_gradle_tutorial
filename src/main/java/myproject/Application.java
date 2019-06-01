package myproject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import myproject.service.ProductService;

@SpringBootApplication
@RestController
@ComponentScan("myproject")
public class Application extends SpringBootServletInitializer implements ApplicationRunner, CommandLineRunner  {

	//Code Structure
	/* myproject
		+ Application
		- modle
			+ Product.java
		- dao
			+ ProductRepository.java
		- controller
			+ ProductController.java
		- service
			+ ProductService.java */
	
	@Autowired //Beans and Dependency Injection
	RestTemplate restTemplate; //Rest Template

	@Bean //Beans and Dependency Injection
	public RestTemplate getRestTemplate() { //Rest Template
		return new RestTemplate();
	}
	
	@Autowired //Beans and Dependency Injection
	ProductService productService;
	
	@Value("${spring.application.my-property:default_value}") //application.properties
	String myProperty;
	
	private static final Logger logger = LoggerFactory.getLogger(Application.class); // Logging

	//SpringBootServletInitializer - for Tomcat war deployment - 
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) { //need to extends SpringBootServletInitializer
		return application.sources(Application.class);
	}

	public static void main(String[] args) { //main class to startup Spring boot app
		SpringApplication.run(Application.class, args);
	}

	@RequestMapping(value = "/")
	public String hello() {
		return "Hello World from Tomcat";
	}

	@RequestMapping(value = "/api/demo")
	public String demoAPI() {
		return "demo api";
	}

	@RequestMapping(value = "/rest-template/demo")
	public String demoRestTemplate() {
		return "demo rest template: " + getRestTemplate(); //Rest Template
	}

	//Application Runner is an interface used to execute the code after the Spring Boot application started.
	@Override
	public void run(ApplicationArguments args) throws Exception { //ApplicationRunner - need to implements ApplicationRunner
		System.out.println("Hello World from Application Runner");
	}
	
	// Similar to Application Runner but accept String[] Arguments instead
    @Override
    public void run(String... arg0) throws Exception { // CommandLineRunner - need to implements CommandLineRunner
       System.out.println("Hello world from Command Line Runner");
       for(String arg: arg0) { //set Program arguments in Run Configuration eclipse
    	   System.out.println(arg);
       }
    }
	
	@RequestMapping(value = "/my-property/demo")
	public String demoMyProperty() { //application.properties
		return "demo my property: " + myProperty;
	}

	@RequestMapping(value = "/logger/demo")
	public String demoLogger() { // Logging
		// logger level
		logger.debug("LOGGER-DEMO: DEBUG");
		logger.info("LOGGER-DEMO: INFO");
		logger.warn("LOGGER-DEMO: WARN");
		return "demo logger";
	}

   //CORS Support
   @Bean
   public WebMvcConfigurer corsConfigurer() { //CORS configuration globally in main Spring Boot application 
      return new WebMvcConfigurerAdapter() {
         @Override
         public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/products").allowedOrigins("http://localhost:8080");
            registry.addMapping("/template/products").allowedOrigins("http://localhost:8080");
         }
      };
   }
   
   //CORS Support
   @RequestMapping(value = "/demo/products")
   @CrossOrigin(origins = "http://localhost:8081") // Enable CORS supports specific REST API, and not for the entire application.
   public ResponseEntity<Object> getProduct() {
	   return new ResponseEntity<>(productService.getProducts(), HttpStatus.OK);
   }
}
