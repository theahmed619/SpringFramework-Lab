package com.example.yeshendrayt.service;

import java.time.LocalDate;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.yeshendrayt.entity.Book;
import com.example.yeshendrayt.entity.IssueRecord;
import com.example.yeshendrayt.entity.User;
import com.example.yeshendrayt.repository.BookRepo;
import com.example.yeshendrayt.repository.IssueRecordRepo;
import com.example.yeshendrayt.repository.UserRepo;

@Service
public class IssueRecordService {

	@Autowired
	private IssueRecordRepo issueRecordRepo;

	@Autowired
	private BookRepo bookRepo;

	@Autowired
	private UserRepo userRepo;

	public IssueRecord issueTheBook(Long bookId) {
		Book book = bookRepo.findById(bookId).orElseThrow(() -> new RuntimeException("Book Not Found"));

		if (book.getQuantity() <= 0 || !book.getIsAvailable()) {
			throw new RuntimeException("Book is not available");
		}

		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepo.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));

		IssueRecord issueRecord = new IssueRecord();
		issueRecord.setIssueDate(LocalDate.now());
		issueRecord.setDueDate(LocalDate.now().plusDays(14));
		issueRecord.setIsReturned(false);

		issueRecord.setUser(user);
		issueRecord.setBook(book);

		book.setQuantity(book.getQuantity() - 1);
		if (book.getQuantity() == 0) {
			book.setIsAvailable(false);
		}

		bookRepo.save(book);
		return issueRecordRepo.save(issueRecord);

	}

	public IssueRecord returnTheBook(Long issueRecordId) {
		IssueRecord issueRecord = issueRecordRepo.findById(issueRecordId)
				.orElseThrow(() -> new RuntimeException("Issue Record not found"));
		
		if(issueRecord.getIsReturned()) {
			throw new RuntimeException("Book is already returned");
		}
		
		Book book=issueRecord.getBook();
		book.setQuantity(book.getQuantity()+1);
		book.setIsAvailable(true);
		bookRepo.save(book);
		
		issueRecord.setReturnDate(LocalDate.now());
		issueRecord.setIsReturned(true);
		
		return issueRecordRepo.save(issueRecord);
	}

}
