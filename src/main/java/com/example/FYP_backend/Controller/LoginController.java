package com.example.FYP_backend.Controller;

import com.example.FYP_backend.DAO.UmsAdminRepository;
import com.example.FYP_backend.DAO.UmsMemberRepository;
import com.example.FYP_backend.DAO.UmsStoreOwnerRepository;
import com.example.FYP_backend.Model.UmsAdmin;
import com.example.FYP_backend.Model.UmsMember;
import com.example.FYP_backend.Model.UmsStoreOwner;
import com.example.FYP_backend.Model.User;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class LoginController {
    @Resource
    private UmsStoreOwnerRepository storeOwnerRepo;

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




    @PostMapping("/adminLogin")
    public boolean adminLogin(@RequestBody User user) {
        UmsAdmin admin = adminRepo.findByUsernameEquals(user.getUsername());
        if (admin != null) {
            return admin.getPassword().equals(user.getPassword());
        }else {
            return false;
        }
    }
    @PostMapping("/ownerSignUp")
    public void ownerSignUp(@RequestBody User user) {
        int id = generateOwnerID();
        UmsStoreOwner owner = new UmsStoreOwner();
        owner.setId(id);
        owner.setUsername(user.getUsername());
        owner.setPassword(user.getPassword());
        storeOwnerRepo.save(owner);
    }

    public int generateOwnerID(){
        List<UmsStoreOwner> storeOwners = storeOwnerRepo.findAll(Sort.by(Sort.Direction.DESC, "id"));
        if (storeOwners.isEmpty()) {
            return 1;
        } else {
            return storeOwners.get(0).getId() + 1;
        }
    }
}



