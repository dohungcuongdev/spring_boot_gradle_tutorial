package hystrixapp;

import org.springframework.boot.SpringApplication;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/*
 * Hystrix is a library from Netflix. Hystrix isolates the points of access between the services, 
 * stops cascading failures across them and provides the fallback options.
 * For example, when you are calling a 3rd party application,
 * it takes more time to send the response. So at that time, 
 * the control goes to the fallback method and returns the custom response to your application.
 */

@SpringBootApplication
@EnableHystrix
@RestController
public class HystrixappApplication { // Hystrix
	
	public static void main(String[] args) {
		SpringApplication.run(HystrixappApplication.class, args);
	}

	@RequestMapping(value = "/")
	@HystrixCommand(fallbackMethod = "fallback_hello", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000") })
	public String hello() throws InterruptedException {
		Thread.sleep(3000);
		return "Welcome Hystrix";
	}

	//define the fallback method fallback_hello() if the request takes a long time to respond.
	private String fallback_hello() {
		return "Request fails. It takes long time to response";
	}
}
