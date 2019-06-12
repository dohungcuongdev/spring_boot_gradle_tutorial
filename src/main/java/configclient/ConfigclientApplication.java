package configclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RefreshScope //The @RefreshScope annotation is used to load the configuration properties value from the Config server.
@RestController
public class ConfigclientApplication { //Cloud Configuration Client
   @Value("${welcome.message}")
   String welcomeText;
   
   // test - make sure ConfigserverApplication is running correctly on port 8888
   // run this class with port 9090
   public static void main(String[] args) {
      SpringApplication.run(ConfigclientApplication.class, args);
   }
   @RequestMapping(value = "/")
   public String welcomeText() {
      return welcomeText;
   }
}
