package com.aggasy.actdrl.service;

import com.aggasy.actdrl.domain.User;  

public interface UserService {  
      
    public int addUser(User user);  
      
    public User findUserById(Integer userId);  
}
