package com.example.FYP_backend.Controller;

import com.example.FYP_backend.DAO.UmsMemberReceiveAddressRepository;
import com.example.FYP_backend.DAO.UmsMemberRepository;
import com.example.FYP_backend.DAO.UmsMemberStatisticsInfoRepository;
import com.example.FYP_backend.Model.UmsMember;
import com.example.FYP_backend.Model.UmsMemberReceiveAddress;
import com.example.FYP_backend.Model.UmsMemberStatisticsInfo;
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
    @Resource
    private UmsMemberRepository memberRepo;
    @Resource
    private UmsMemberStatisticsInfoRepository statisticsRepo;

    @RequestMapping(value = "/getAddresses/{id}")
    public List<UmsMemberReceiveAddress> getAddresses(@PathVariable int id){
        return addressRepo.findByMemberIdEquals(id);
    }

    @RequestMapping(value = "/updateAddress")
    public void updateAddress(@RequestBody UmsMemberReceiveAddress address){
        addressRepo.save(address);
    }

    @RequestMapping(value = "/deleteAddress/{id}")
    public void deleteAddress(@PathVariable int id){
        addressRepo.deleteById(id);
    }

    @RequestMapping(value = "/userProfile/{id}")
    public UmsMember userProfile(@PathVariable int id){
        return memberRepo.findByIdEquals(id);
    }

    @RequestMapping(value = "/updateProfile")
    public void updateProfile(@RequestBody UmsMember member){
        memberRepo.save(member);
    }

    @RequestMapping(value = "/userStatistics/{id}")
    public UmsMemberStatisticsInfo userStatistics(@PathVariable int id){
        return statisticsRepo.findByMember_IdEquals(id);
    }
}
