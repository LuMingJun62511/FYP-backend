package com.example.FYP_backend.DAO;

import com.example.FYP_backend.Model.UmsAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UmsAdminRepository extends JpaRepository<UmsAdmin, Integer> {
    UmsAdmin findByUsernameEquals(String username);

}
