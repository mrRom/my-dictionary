package com.mr.user.dao;

import java.io.IOException;
import java.util.List;

import com.mr.user.model.DUser;
import com.mr.util.search.JQGridFilter;

public interface UserDao {
	void save(DUser user);
	void update(DUser user);
	void delete(DUser user);
	DUser loadUserByUsername(String name);
	DUser loadUserById(int id);
	List<DUser> loadAllUsers();
	List<DUser> findUserbygivenCriteria(JQGridFilter filter) throws IOException;
}
