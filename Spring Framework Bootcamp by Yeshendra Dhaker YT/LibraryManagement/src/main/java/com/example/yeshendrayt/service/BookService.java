package com.example.yeshendrayt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.yeshendrayt.dto.BookDTO;
import com.example.yeshendrayt.entity.Book;
import com.example.yeshendrayt.repository.BookRepo;

@Service
public class BookService {

	@Autowired
	private BookRepo bookRepo;

	public List<Book> getAllBooks() {
		return bookRepo.findAll();
	}

	public Book getBookById(Long id) {
		Book book = bookRepo.findById(id).orElseThrow(() -> new RuntimeException("Book Not Found"));

		return book;
	}

	public Book addBook(BookDTO bookDTO) {

		Book book = new Book();
		book.setTitle(bookDTO.getTitle());
		book.setAuthor(bookDTO.getAuthor());
		book.setIsbn(bookDTO.getIsbn());
		book.setIsAvailable(bookDTO.getIsAvailable());
		book.setQuantity(bookDTO.getQuantity());

		return bookRepo.save(book);
	}

	public Book updateBook(Long id, BookDTO bookDTO) {

		Book oldBook = bookRepo.findById(id).orElseThrow(() -> new RuntimeException("Book not Found"));
	
		oldBook.setTitle(bookDTO.getTitle());
		oldBook.setAuthor(bookDTO.getAuthor());
		oldBook.setIsbn(bookDTO.getIsbn());
		oldBook.setIsAvailable(bookDTO.getIsAvailable());
		oldBook.setQuantity(bookDTO.getQuantity());
		
		return bookRepo.save(oldBook);

	}
	
	public void deleteBookById(Long id) {
		  bookRepo.deleteById(id);
	}

}
