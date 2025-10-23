package com.example.yeshendrayt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.yeshendrayt.entity.UserDetails;

@Repository
public interface UserDetailRepo extends JpaRepository<UserDetails, Long> {

}
