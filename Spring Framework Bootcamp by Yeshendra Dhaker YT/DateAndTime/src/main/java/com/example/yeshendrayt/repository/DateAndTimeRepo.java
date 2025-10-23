package com.example.yeshendrayt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.yeshendrayt.entity.DateAndTime;

@Repository
public interface DateAndTimeRepo extends JpaRepository<DateAndTime, Long>{

}
