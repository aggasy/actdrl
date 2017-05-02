package com.aggasy.actdrl.web.user;

import java.util.Date;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aggasy.actdrl.domain.User;
import com.aggasy.actdrl.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	private final Log logger = LogFactory.getLog(UserController.class);

	@Autowired
	private UserService userService;

	@RequestMapping("/index")
	public String userIndex() {

		return "user/index";
	}
	
	@RequestMapping("/")
	public String index() {

		return "user/index";
	}

	@RequestMapping("/getUserInfo")
	public String getUserInfo(Integer userId, Map<String, Object> map) {

		logger.info("获取用户信息请求参数 userId=" + userId);
		User user = userService.findUserById(userId);
		map.put("userInfo", user);
		return "user/show";
	}

	@RequestMapping("/addInfo")
	public String addInfo(User user, Map<String, Object> map) {
		user.setRegisterDate(new Date());
		logger.info("添加用户信息请求参数:" + user);
		int result = userService.addUser(user);
		map.put("addRes", result);
		return "user/index";
	}
}
