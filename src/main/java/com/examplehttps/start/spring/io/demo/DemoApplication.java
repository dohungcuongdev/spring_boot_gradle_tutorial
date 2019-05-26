package com.examplehttps.start.spring.io.demo;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
@ComponentScan("myproject")
public class DemoApplication extends SpringBootServletInitializer implements ApplicationRunner {

	@Autowired
	RestTemplate restTemplate;
	
	@Value("${spring.application.my-property:default_value}")
	String myProperty;
	
	private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(DemoApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
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
}
