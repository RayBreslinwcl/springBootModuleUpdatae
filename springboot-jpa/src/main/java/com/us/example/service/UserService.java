package com.us.example.service;

import java.util.List;
import java.util.Map;

import com.us.example.bean.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

/**
 * The Interface UserService.
 */
public interface UserService {

	/**
	 * Gets the user by name.
	 *
	 * @param username the user name
	 * @return the user by name
	 */
	public User getUserByName(String username);
	
	public List<User> findAll();

	public List<User> findAllByAddress(String address);
}
