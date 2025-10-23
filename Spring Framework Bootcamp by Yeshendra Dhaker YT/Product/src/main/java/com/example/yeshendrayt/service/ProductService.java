package com.example.yeshendrayt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.yeshendrayt.entity.Product;
import com.example.yeshendrayt.exception.DuplicateProductException;
import com.example.yeshendrayt.repo.ProductRepo;

@Service
public class ProductService {
	
	@Autowired
	ProductRepo productRepo;
	
	public ResponseEntity<?> saveProduct(Product product){
		
		if(productRepo.findByName(product.getName()).isPresent()) {
			throw new DuplicateProductException("Product by the name "+product.getName()+" is Already Exist");
		}
		productRepo.save(product);
		return new ResponseEntity<>(product, HttpStatus.CREATED);
	}
	
	public List<Product> getAllProduct(){
		return productRepo.findAll();
	}

}
