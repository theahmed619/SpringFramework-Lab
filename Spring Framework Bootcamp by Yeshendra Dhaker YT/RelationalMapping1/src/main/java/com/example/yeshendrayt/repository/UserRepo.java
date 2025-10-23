package com.example.yeshendrayt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.yeshendrayt.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long>{

}
