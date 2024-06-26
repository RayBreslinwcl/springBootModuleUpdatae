package com.us.example.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.us.example.dao.UserJpaDao;
import com.us.example.bean.User;
import com.us.example.service.UserService;

/**
 * 
 * @ClassName UserServiceImpl
 * @author abel
 * @date 2016年11月10日
 */
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserJpaDao userJpaDao;
	/**
	 * 
	 * @param username
	 * @return
	 */
	@Override
	public User getUserByName(String username) {
		return userJpaDao.findByName(username);
	}

	@Override
	public List<User> findAll() {
		return userJpaDao.findAll();
	}

	@Override
	public List<User> findAllByAddress(String address) {
		return userJpaDao.findAllByAddress(address);
	}

	@Override
	public List<User> findAllByAddressAndName(String address, String name) {
		return userJpaDao.findAllByAddressAndName(address,name);
	}

	@Override
	public List<User> findAllByInAddress(List<String> addresses) {
		return userJpaDao.findAllByInAddress(addresses);
	}

	@Override
	public List<User> findAllByAddressInSet(String address) {
		return userJpaDao.findAllByAddressInSet(address);
	}

	public void addUser(User user) {
		userJpaDao.save(user);
	}
}

