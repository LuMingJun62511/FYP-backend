package com.example.FYP_backend.DAO;


import com.example.FYP_backend.Model.SmsShelf;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SmsShelfRepository extends JpaRepository<SmsShelf, Integer> {
    SmsShelf findByIdEquals(Integer id);

    List<SmsShelf> findAll();
}
