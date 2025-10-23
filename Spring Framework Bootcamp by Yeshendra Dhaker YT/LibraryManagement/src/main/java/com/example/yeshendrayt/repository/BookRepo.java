package com.example.yeshendrayt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.yeshendrayt.entity.Book;

@Repository
public interface BookRepo extends JpaRepository<Book, Long>{

}
