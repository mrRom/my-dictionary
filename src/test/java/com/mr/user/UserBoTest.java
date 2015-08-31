package com.mr.user;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mr.user.bo.UserBo;
import com.mr.user.model.DUser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/config/BeanLocations.xml"})

public class UserBoTest {
	@Autowired
	UserBo userBo;
	
	@Before
	//@Test
	public void insertUserTest() {
		DUser user = new DUser();
		user.setUserName("test13");
		user.setUserPassword("test13");
		user.setUserEmail("test@test.ua");
		user.setAccess(2);
		userBo.save(user);
	}
	@Test (expected=Exception.class)
	public void insertUserExceptionTest() {
		DUser user = new DUser();
		user.setUserName("test13");
		user.setUserPassword("test13");
		user.setUserEmail("test@test.ua");
		user.setAccess(2);
		userBo.save(user);
	}
	
	@Test
	public void loadUserByUsernameTest() {
		assertEquals("test13", userBo.loadUserByUsername("test13").getUserName());
	}
	
	@After
	//@Test
	public void deleteUserTest() {
		DUser user = (DUser) userBo.loadUserByUsername("test13");
		userBo.delete(user);
	}
}
