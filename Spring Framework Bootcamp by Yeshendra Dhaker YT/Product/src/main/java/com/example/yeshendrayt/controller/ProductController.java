package com.example.yeshendrayt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.yeshendrayt.entity.Product;
import com.example.yeshendrayt.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@PostMapping("/createproduct")
	public ResponseEntity<?> createProduct(@RequestBody Product product){
		
		 return productService.saveProduct(product);
		
	}
	
	@GetMapping("/getproduct")
	public List<Product> getAllProduct(){
		
		 return productService.getAllProduct();
		
	}

}
