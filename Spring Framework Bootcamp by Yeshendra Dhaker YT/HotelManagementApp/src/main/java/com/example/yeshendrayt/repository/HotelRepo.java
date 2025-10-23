package com.example.yeshendrayt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.yeshendrayt.entity.Hotel;

@Repository
public interface HotelRepo extends JpaRepository<Hotel, Long>{

}
