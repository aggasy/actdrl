package com.aggasy.actdrl.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aggasy.actdrl.dao.UserDao;
import com.aggasy.actdrl.domain.User;

@Repository
public class UserDaoImpl implements UserDao {
	@Autowired
	private SqlSessionTemplate sqlSession;

	public int addUser(User user) {
		return sqlSession.insert("User.addUser", user);
	}

	public User findUserById(Integer userId) {
		return (User) sqlSession.selectOne("User.findUserById", userId);
	}
}