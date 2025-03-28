package com.chatBat.Dao;

import java.util.List;

import com.chatBat.Dao.entity.User;



public interface IUserDao {

	public List<User> getAllUsers();
	
	
	public void save(User user);
}
