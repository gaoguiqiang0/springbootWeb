package com.example.gao.controller;

import com.example.gao.domain.User;
import com.example.gao.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.UUID;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/getUser1")
    @Cacheable(value="user-key")
    public User getUser() {

        User user=userRepository.findByUserName("aa");

        System.out.println("若下面没出现“无缓存的时候调用”字样且能打印出数据表示测试成功");

        return user;

    }

    @RequestMapping("/uid")
    String uid(HttpSession session) {

        UUID uid = (UUID) session.getAttribute("uid");

        if (uid == null) {

            uid = UUID.randomUUID();

        }

        session.setAttribute("uid", uid);

        return session.getId();

    }


}
