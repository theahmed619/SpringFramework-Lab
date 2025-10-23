package com.example.yeshendrayt.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.yeshendrayt.entity.SimpleUser;

public interface UserRepo extends CrudRepository<SimpleUser, Long>{

}
