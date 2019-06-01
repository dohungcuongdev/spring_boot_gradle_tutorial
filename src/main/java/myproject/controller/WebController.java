package myproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController { //Thymeleaf
	
	// run Application.java - test http://localhost:9090/index
   @RequestMapping(value = "/index") // working with src/main/resources/templates/index.html, src/main/resources/static/css/styles/css
   public String index() {
      return "index";
   }
}
