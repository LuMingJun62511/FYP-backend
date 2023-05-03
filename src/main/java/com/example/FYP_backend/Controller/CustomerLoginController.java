package com.example.FYP_backend.Controller;

import com.example.FYP_backend.DAO.UmsMemberRepository;
import com.example.FYP_backend.Model.OmsOrder;
import com.example.FYP_backend.Model.UmsMember;
import com.example.FYP_backend.Model.User;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/api/cp")
public class CustomerLoginController {

    @Resource
    private UmsMemberRepository memberRepo;

    @PostMapping("/memberLogin")
    public boolean userLogin(@RequestBody User user) {
        UmsMember member = memberRepo.findByUsernameEquals(user.getUsername());
        if (member != null) {
            return member.getPassword().equals(user.getPassword());
        }else {
            return false;
        }
    }


    @RequestMapping("/findMember")
    public UmsMember findMember(@RequestBody User user) {
        return memberRepo.findByUsernameEquals(user.getUsername());
    }

    @RequestMapping("/memberSignUp")
    public void memberSignUp(@RequestBody User user) {
        int id = generateMemberID();
        UmsMember member = new UmsMember();
        member.setId(id);
        member.setUsername(user.getUsername());
        member.setPassword(user.getPassword());
        memberRepo.save(member);
    }

    public int generateMemberID(){
        List<UmsMember> members = memberRepo.findAll(Sort.by(Sort.Direction.DESC, "id"));
        if (members.isEmpty()) {
            return 1;
        } else {
            return members.get(0).getId() + 1;
        }
    }

}
