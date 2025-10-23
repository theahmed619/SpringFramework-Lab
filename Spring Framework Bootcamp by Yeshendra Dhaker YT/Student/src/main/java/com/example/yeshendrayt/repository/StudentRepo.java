package com.example.yeshendrayt.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.yeshendrayt.entity.Student;

public interface StudentRepo extends JpaRepository<Student, UUID>{

}
