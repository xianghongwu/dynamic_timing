package com.xhw.dynamictiming.controller;

import com.xhw.dynamictiming.mapper.UserMapper;
import com.xhw.dynamictiming.model.SysJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class UserController {

    @Autowired
    UserMapper userMapper;

    @GetMapping("users")
    @ResponseBody
    public List<SysJob> getUserList(){
        return userMapper.getUserlist();
    }
}
