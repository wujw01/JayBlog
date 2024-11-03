package com.jay.modules.test.users.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jay.common.exception.RRException;
import com.jay.modules.test.entity.Users;

import com.jay.common.util.UserParam;
import com.jay.modules.test.users.service.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jay.xiang
 * @since 2018-09-17
 */
@Slf4j
@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UsersService usersService;

    @RequestMapping("/getUsers")
    public List<Users> getUsers() {
        List<Users> users = usersService.selectList(new EntityWrapper<>());
        return users;
    }

    @RequestMapping("/getList")
    public Page<Users> getList(UserParam userParam) {
        Wrapper ew = new EntityWrapper();
        ew.where("userName={0}", userParam.getUserName())
        .and("user_sex={0}",userParam.getUserSex());
        int total = usersService.selectCount(ew);
        List<Users> usersList = usersService.selectList(ew);
        Page<Users> usersPage = new Page<>(1, 10);
        usersPage.setRecords(usersList);
        usersPage.setTotal(total);
        return usersPage;
    }

    @RequestMapping("/getUser")
    public Users getUser(Long id) {
        Users user=usersService.selectOne(new EntityWrapper<Users>().where("id={0}",id));
        return user;
    }

    @RequestMapping("/add")
    public void save(Users user) {
        usersService.insert(user);
    }

    @RequestMapping(value="update")
    public void update(Users user) {
        usersService.update(user,new EntityWrapper<>());
    }

    /**
     * @return
     * @throws Exception
     */
    @RequestMapping("/exception")
    public String exception() throws Exception {
        throw new RRException("this is exception");
    }

}

