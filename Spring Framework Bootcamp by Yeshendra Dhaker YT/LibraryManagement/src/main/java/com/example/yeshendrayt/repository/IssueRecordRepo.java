package com.example.yeshendrayt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.yeshendrayt.entity.IssueRecord;

@Repository
public interface IssueRecordRepo extends JpaRepository<IssueRecord, Long>{

}
