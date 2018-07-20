package com.hks.spring_boot_hibernate.controller;

import com.hks.spring_boot_hibernate.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("th")
public class ThymeleafController {

	@RequestMapping("/index")
    public String index(ModelMap map) {
        map.addAttribute("name", "thymeleaf-website");
        return "thymeleaf/index";
    }
	
	@RequestMapping("center")
    public String center() {
        return "thymeleaf/center/center";
    }
	
	@RequestMapping("test")
    public String test(ModelMap map) {
		
		User u = new User();
		u.setUsername("i18n");
		u.setPassword("123465");
		u.setAge(18);
		u.setBirthday(new Date());
		
		map.addAttribute("user", u);
		
		User u1 = new User();
		u.setUsername("website");
		u1.setPassword("123456");
		u1.setAge(19);
		u1.setBirthday(new Date());
		
		User u2 = new User();
		u.setUsername("language");
		u2.setPassword("123456");
		u2.setAge(20);
		u2.setBirthday(new Date());
		
		List<User> userList = new ArrayList<>();
		userList.add(u);
		userList.add(u1);
		userList.add(u2);
		
		map.addAttribute("userList", userList);
		
        return "thymeleaf/test";
    }
	
	@PostMapping("postform")
    public String postform(User u) {
		
		System.out.println("姓名：" + u.getUsername());
		
        return "redirect:/th/test";
    }

    //遇到错误跳到自定义页面
	@RequestMapping("showerror")
    public String showerror(User u) {
		
		int a = 1 / 0;
		
        return "redirect:/th/test";
    }
}