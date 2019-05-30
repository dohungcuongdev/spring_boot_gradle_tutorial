package myproject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@RestController
@ComponentScan("myproject")
public class Application extends SpringBootServletInitializer implements ApplicationRunner {

	@Autowired
	RestTemplate restTemplate;
	
	@Value("${spring.application.my-property:default_value}")
	String myProperty;
	
	private static final Logger logger = LoggerFactory.getLogger(Application.class);

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	public static void main(String[] args) {
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

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	@RequestMapping(value = "/rest-template/demo")
	public String demoRestTemplate() {
		return "demo rest template: " + getRestTemplate();
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("Hello World from Application Runner");
	}
	
	@RequestMapping(value = "/my-property/demo")
	public String demoMyProperty() {
		return "demo my property: " + myProperty;
	}

	@RequestMapping(value = "/logger/demo")
	public String demoLogger() {
		logger.debug("LOGGER-DEMO: DEBUG");
		logger.info("LOGGER-DEMO: INFO");
		logger.warn("LOGGER-DEMO: WARN");
		return "demo logger";
	}
	
   @Bean
   public WebMvcConfigurer corsConfigurer() {
      return new WebMvcConfigurerAdapter() {
         @Override
         public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/products").allowedOrigins("http://localhost:8080");
         }
      };
   }
   
   @RequestMapping(value = "/demo/products")
   @CrossOrigin(origins = "http://localhost:8081")
   public ResponseEntity<Object> getProduct() {
      return null;
   }
}
