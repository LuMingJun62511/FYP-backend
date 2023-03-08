package com.example.FYP_backend.Controller;

import com.example.FYP_backend.DAO.UmsAdminRepository;
import com.example.FYP_backend.DAO.UmsMemberRepository;
import com.example.FYP_backend.DAO.UmsStoreOwnerRepository;
import com.example.FYP_backend.Model.UmsAdmin;
import com.example.FYP_backend.Model.UmsMember;
import com.example.FYP_backend.Model.UmsStoreOwner;
import com.example.FYP_backend.Model.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class LoginController {
    @Resource
    private UmsStoreOwnerRepository storeOwnerRepo;
    @Resource
    private UmsMemberRepository memberRepo;
    @Resource
    private UmsAdminRepository adminRepo;

    @PostMapping("/ownerLogin")
    public boolean ownerLogin(@RequestBody User user) {
        UmsStoreOwner owner = storeOwnerRepo.findByUsernameEquals(user.getUsername());
        if (owner != null) {
            return owner.getPassword().equals(user.getPassword());
        }else {
            return false;
        }
    }
    @PostMapping("/memberLogin")
    public boolean userLogin(@RequestBody User user) {
        UmsMember member = memberRepo.findByUsernameEquals(user.getUsername());
        if (member != null) {
            return member.getPassword().equals(user.getPassword());
        }else {
            return false;
        }
    }

    @PostMapping("/adminLogin")
    public boolean adminLogin(@RequestBody User user) {
        UmsAdmin admin = adminRepo.findByUsernameEquals(user.getUsername());
        if (admin != null) {
            return admin.getPassword().equals(user.getPassword());
        }else {
            return false;
        }
    }

}



