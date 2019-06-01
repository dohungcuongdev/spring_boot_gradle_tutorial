package myproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import myproject.model.Product;
import myproject.service.ProductService;

//run Application.java - test http://localhost:9090/products or http://localhost:9090/products/[ID] :E.g http://localhost:9090/products/1
@RestController
public class ProductServiceController { // Building RESTful Web Services - Service Components
	@Autowired
	ProductService productService;

	@RequestMapping(value = "/products")
	public ResponseEntity<Object> getProduct() { // working with src/main/resources/templates/view-products.html
		return new ResponseEntity<>(productService.getProducts(), HttpStatus.OK);
	}

	@RequestMapping(value = "/products", method = RequestMethod.POST)
	public ResponseEntity<Object> createProduct(@RequestBody Product product) { // working with src/main/resources/templates/add-products.html
		productService.createProduct(product);
		return new ResponseEntity<>("Product is created successfully", HttpStatus.CREATED);
	}

	@RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateProduct(@PathVariable("id") String id, @RequestBody Product product) {

		productService.updateProduct(id, product);
		return new ResponseEntity<>("Product is updated successsfully", HttpStatus.OK);
	}

	@RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> delete(@PathVariable("id") String id) {
		productService.deleteProduct(id);
		return new ResponseEntity<>("Product is deleted successsfully", HttpStatus.OK);
	}
}