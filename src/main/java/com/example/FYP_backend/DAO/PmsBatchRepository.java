package com.example.FYP_backend.DAO;

import com.example.FYP_backend.Model.PmsBatch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PmsBatchRepository extends JpaRepository<PmsBatch, Integer> {
    List<PmsBatch> findByAbstractProduct_IdEquals(Integer id);
}
