package com.niit.dao;

import java.util.List;

import com.niit.model.User;

public interface UserDao {

	void registration(User user);
	User login(User user);

	List<User> getAllUsers();
	public User updateUser(User validUser) ;
}
