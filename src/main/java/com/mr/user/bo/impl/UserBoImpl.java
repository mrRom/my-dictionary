package com.mr.user.bo.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mr.user.bo.UserBo;
import com.mr.user.dao.UserDao;
import com.mr.user.model.DUser;
import com.mr.util.search.JQGridFilter;

@Service("userBo")
public class UserBoImpl implements UserBo{
	
	@Autowired
	UserDao userDao;
	
	@Override
	public void save(DUser user) {
		userDao.save(user);	
	}

	@Override
	public void update(DUser user) {
		userDao.update(user);	
	}

	@Override
	public void delete(DUser user) {
		userDao.delete(user);
	}

	@Override
	public DUser loadUserByUsername(String name) {
		return userDao.loadUserByUsername(name);
	}

	@Override
	public DUser loadUserById(int id) {
		return userDao.loadUserById(id);
	}

	@Override
	public List<DUser> loadAllUsers() {
		return userDao.loadAllUsers();
	}

	@Override
	public List<DUser> findUserbygivenCriteria(JQGridFilter filter)
			throws IOException {
		return userDao.findUserbygivenCriteria(filter);
	}
}
