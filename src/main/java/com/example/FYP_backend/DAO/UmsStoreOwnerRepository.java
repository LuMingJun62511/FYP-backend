package com.example.FYP_backend.DAO;

import com.example.FYP_backend.Model.UmsStoreOwner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UmsStoreOwnerRepository extends JpaRepository<UmsStoreOwner, Integer> {
    UmsStoreOwner findByUsernameEquals(String username);
}
