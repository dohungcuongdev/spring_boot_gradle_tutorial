package myproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController { //Thymeleaf - Consuming RESTful Web Services
	
	// run Application.java - test http://localhost:9090/view-products
	@RequestMapping("/view-products") //working with src/main/resources/templates/view-products.html
	public String viewProducts() {
		return "view-products";
	}

	// run Application.java - test http://localhost:9090/add-products
	@RequestMapping("/add-products") //working with src/main/resources/templates/add-products.html
	public String addProducts() {
		return "add-products";
	}

	// Internationalization
	// run Application.java - test http://localhost:9090/locale or http://localhost:9090/locale?language=fr
	@RequestMapping("/locale") // working with src/main/resources/templates/locale.html
	public String locale() {
		return "locale";
	}
	
	@RequestMapping("/upload-file") // working with src/main/resources/templates/upload-file.html
	public String uploadFile() {
		return "upload-file";
	}
}