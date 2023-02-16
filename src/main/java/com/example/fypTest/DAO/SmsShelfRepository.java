package com.example.fypTest.DAO;

import com.example.fypTest.Model.SmsShelf;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SmsShelfRepository extends JpaRepository<SmsShelf, Integer> {
    SmsShelf findByIdEquals(Integer id);
}
