package com.hks.spring_boot_hibernate.controller;

import com.hks.spring_boot_hibernate.entity.User;
import com.hks.spring_boot_hibernate.repository.UserRepository;
import com.hks.spring_boot_hibernate.utils.JSONResultUtils;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Date;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Sid sid;//注入自动生成id类

    @RequestMapping("/getUserJson")
    public JSONResultUtils getUserJson(){
        return JSONResultUtils.ok("hello world");
    }

    //添加用户
    @RequestMapping("/addUser")
    @Transactional
    public JSONResultUtils addUser(@Valid User user , BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            System.out.print(bindingResult.getFieldError().getDefaultMessage());
            return null;
        }

        user.setId(sid.nextShort());
        user.setUsername(user.getUsername());
        user.setAge(user.getAge());
        user.setPassword("123456");
        user.setBirthday(new Date());
        userRepository.save(user);
        //int a = 1/0;
//        User user1 = new User();
//        user1.setId(sid.nextShort());
//        user1.setUsername("test1");
//        user1.setPassword("123456");
//        user1.setBirthday(new Date());
//        userRepository.save(user1);
        return JSONResultUtils.ok("保存成功");
    }

    //删除用户
    @RequestMapping("/deleteUser")
    public JSONResultUtils deleteUser(@RequestParam("userid") String userid){
        userRepository.deleteById(userid);
        return JSONResultUtils.ok("删除成功");
    }

    //修改用户
    @RequestMapping("/updateUser")
    public JSONResultUtils updateUser(){
        User user = new User();
        user.setId("1807198HK0B87F80");
        user.setUsername("test2");
        userRepository.save(user);
        return JSONResultUtils.ok("修改成功");
    }

    //查询用户
    @RequestMapping("/selectUser")
    public String selectUser(@RequestParam("userid") String userid){
        //return JSONResultUtils.ok(userRepository.findById(userid));
        return "user";
    }

    //jpa自定义查询
    @RequestMapping("/customUserSelect")
    public JSONResultUtils customUserSelect(@RequestParam("username") String username){
        return JSONResultUtils.ok(userRepository.findByUsername(username));
    }

    //sql语句查询
    @RequestMapping("/sqlUsername")
    public JSONResultUtils sqlSelectUsername(@RequestParam("username") String username){
        return JSONResultUtils.ok(userRepository.findUsername(username));
    }

}
