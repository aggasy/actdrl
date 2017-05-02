package com.aggasy.actdrl.dao;

import com.aggasy.actdrl.domain.User;

public interface UserDao {

	public int addUser(User user);

	public User findUserById(Integer userId);
}
