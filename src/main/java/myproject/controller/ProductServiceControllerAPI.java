package myproject.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import myproject.exception.ProductNotfoundException;
import myproject.model.Product;

@RestController
@RequestMapping("/api")
public class ProductServiceControllerAPI {
	private static Map<String, Product> productRepo = new HashMap<>();
	static {
		Product honey = new Product();
		honey.setId("1");
		honey.setName("Honey");
		productRepo.put(honey.getId(), honey);
		Product almond = new Product();
		almond.setId("2");
		almond.setName("Almond");
		productRepo.put(almond.getId(), almond);
	}

	@CrossOrigin
	@RequestMapping(value = "/products", produces = "application/json; charset=UTF-8")
	public ResponseEntity<Object> getProduct() {
		System.out.println("getProduct is Calling");
		return new ResponseEntity<>(productRepo.values(), HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/products", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public ResponseEntity<Object> createProduct(@RequestBody Product product) {
		System.out.println("createProduct is Calling");
		productRepo.put(product.getId(), product);
		return new ResponseEntity<>("Product is created successfully", HttpStatus.CREATED);
	}

	@CrossOrigin
	@RequestMapping(value = "/products/{id}", method = RequestMethod.PUT, produces = "application/json; charset=UTF-8")
	public ResponseEntity<Object> updateProduct(@PathVariable("id") String id, @RequestBody Product product) {
		System.out.println("updateProduct is Calling");
		if (!productRepo.containsKey(id))
			throw new ProductNotfoundException();
		productRepo.remove(id);
		product.setId(id);
		productRepo.put(id, product);
		return new ResponseEntity<>("Product is updated successfully", HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE, produces = "application/json; charset=UTF-8")
	public ResponseEntity<Object> delete(@PathVariable("id") String id) {
		System.out.println("delete is Calling");
		productRepo.remove(id);
		return new ResponseEntity<>("Product is deleted successsfully", HttpStatus.OK);
	}
}