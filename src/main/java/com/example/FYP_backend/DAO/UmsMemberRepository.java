package com.example.FYP_backend.DAO;

import com.example.FYP_backend.Model.UmsMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UmsMemberRepository extends JpaRepository<UmsMember, Integer> {
    UmsMember findByUsernameEquals(String username);
}
