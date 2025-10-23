package com.example.yeshendrayt.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.yeshendrayt.entity.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long>{

	//using fields
	Optional<Product> findByName(String name);
	//List<Product> findByPrice(Double price);
	
	//combining fields
	//List<Product> findByNameAndPrice(String name, Double price);
	
	//using operator
//	List<Product> findByPriceGreaterThan(Double price);
//	List<Product> findByNameContaining(String name);
}
