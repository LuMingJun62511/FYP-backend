package com.example.fypTest.DAO;

import com.example.fypTest.Model.PmsBatch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PmsBatchRepository extends JpaRepository<PmsBatch, Integer> {
    List<PmsBatch> findByAbstractProduct_IdEquals(Integer id);
}
