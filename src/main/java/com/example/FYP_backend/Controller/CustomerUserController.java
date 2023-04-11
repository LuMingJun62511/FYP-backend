package com.example.FYP_backend.Controller;

import com.example.FYP_backend.DAO.UmsMemberReceiveAddressRepository;
import com.example.FYP_backend.Model.UmsMemberReceiveAddress;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/api/cp")
public class CustomerUserController {
    @Resource
    private UmsMemberReceiveAddressRepository addressRepo;

    @RequestMapping(value = "/getAddresses/{id}")
    public List<UmsMemberReceiveAddress> getAddresses(@PathVariable int id){
        return addressRepo.findByMemberIdEquals(id);
    }

    @RequestMapping(value = "/updateAddress")
    public void updateAddress(@RequestBody UmsMemberReceiveAddress address){
        addressRepo.save(address);
    }

}
