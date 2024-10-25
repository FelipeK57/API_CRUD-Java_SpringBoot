package com.myproject.crud.product;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {

	private final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

    @GetMapping
	public List<Product> getProducts() {
		return this.productService.getProducts();
	}

	@PostMapping
	public ResponseEntity<String> addProduct(@RequestBody Product product) {
		return this.productService.newProduct(product);
	}

	@PutMapping
	public ResponseEntity<String> putMethodName(@RequestBody Product product) {
		return this.productService.updateProduct(product);
	}

	@DeleteMapping(path = "{productId}")
	public ResponseEntity<String> deleteProduct(@PathVariable("productId") Long id) {
		return this.productService.deleteProduct(id);
	}
}
