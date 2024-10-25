package com.myproject.crud.product;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts() {
		return productRepository.findAll();
	}

    public ResponseEntity<String> newProduct(Product product) {
        Optional<Product> res = productRepository.findProductByName(product.getName());
        if (res.isPresent()) {
            return new ResponseEntity<>("Product already exists", HttpStatus.BAD_REQUEST);
        }
        productRepository.save(product);
        return new ResponseEntity<>("Product added", HttpStatus.CREATED);
    }

    public ResponseEntity<String> updateProduct(Product product) {
        Optional<Product> res = productRepository.findById(product.getId());
        if (res.isEmpty()) {
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }
        productRepository.save(product);
        return new ResponseEntity<>("Product updated", HttpStatus.OK);
    }

    public ResponseEntity<String> deleteProduct(Long id){
        Optional<Product> res = productRepository.findById(id);
        if (res.isEmpty()) {
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }
        productRepository.deleteById(id);
        return new ResponseEntity<>("Product deleted", HttpStatus.OK);
    }
}
