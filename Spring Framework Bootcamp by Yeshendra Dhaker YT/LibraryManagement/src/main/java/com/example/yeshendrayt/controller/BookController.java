package com.example.yeshendrayt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.yeshendrayt.dto.BookDTO;
import com.example.yeshendrayt.entity.Book;
import com.example.yeshendrayt.service.BookService;

@RestController
@RequestMapping("/books")
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	
	@GetMapping("/getallbooks")
	public ResponseEntity<List<Book>> getAllBooks(){
		
		return ResponseEntity.ok(bookService.getAllBooks());
	}
	
	@GetMapping("/getbookbyid/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable Long id){
		
		return ResponseEntity.ok(bookService.getBookById(id));
	}
	
	@PostMapping("/addbook")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Book> addBook(@RequestBody BookDTO bookDTO){
		
		return ResponseEntity.ok(bookService.addBook(bookDTO));
	}
	
	@PutMapping("/updatebook/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Book> updateBook(@PathVariable Long id,@RequestBody BookDTO bookDTO){
		
		return ResponseEntity.ok(bookService.updateBook(id,bookDTO));
	}
	
	@DeleteMapping("/deletebook/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Void> deleteBook(@PathVariable Long id){
		
		bookService.deleteBookById(id);
		return ResponseEntity.ok().build();
	}
	
	

}
