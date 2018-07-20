package com.hks.spring_boot_hibernate.controller;

import com.hks.spring_boot_hibernate.entity.User;
import com.hks.spring_boot_hibernate.utils.JSONResultUtils;
import com.hks.spring_boot_hibernate.utils.JsonUtils;
import com.hks.spring_boot_hibernate.utils.RedisOperator;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("redis")
public class RedisController {

	@Autowired
	private StringRedisTemplate strRedis;

	@Autowired
	private RedisOperator redis;

	@Autowired
	private Sid sid;

	@RequestMapping("/test")
	public JSONResultUtils test() {

		//reids set字符串
		strRedis.opsForValue().set("website", "hello language~~~~~~");

		User user = new User();
		user.setId(sid.nextShort());
		user.setUsername("website");
		user.setPassword("abc123");
		//redis set对象
		strRedis.opsForValue().set("json:user", JsonUtils.objectToJson(user));
		//redis 获取
		User jsonUser = JsonUtils.jsonToPojo(strRedis.opsForValue().get("json:user"), User.class);

		return JSONResultUtils.ok(jsonUser);
	}

	@RequestMapping("/getJsonList")
	public JSONResultUtils getJsonList() {

		User user = new User();
		user.setAge(18);
		user.setUsername("website");
		user.setPassword("123456");
		user.setBirthday(new Date());

		User u1 = new User();
		u1.setAge(19);
		u1.setUsername("language");
		u1.setPassword("123456");
		u1.setBirthday(new Date());

		User u2 = new User();
		u2.setAge(17);
		u2.setUsername("birthday");
		u2.setPassword("123456");
		u2.setBirthday(new Date());

		List<User> userList = new ArrayList<>();
		userList.add(user);
		userList.add(u1);
		userList.add(u2);

		redis.set("json:info:userlist", JsonUtils.objectToJson(userList), 2000);

		String userListJson = redis.get("json:info:userlist");
		List<User> userListBorn = JsonUtils.jsonToList(userListJson, User.class);

		return JSONResultUtils.ok(userListBorn);
	}
}