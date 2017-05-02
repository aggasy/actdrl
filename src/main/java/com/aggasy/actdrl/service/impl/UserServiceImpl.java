package com.aggasy.actdrl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aggasy.actdrl.dao.UserDao;
import com.aggasy.actdrl.domain.User;
import com.aggasy.actdrl.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	public int addUser(User user) {
		return userDao.addUser(user);
	}

	public User findUserById(Integer userId) {
		return userDao.findUserById(userId);
	}

}
