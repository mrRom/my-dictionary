package com.mr.user;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mr.user.bo.UserBo;
import com.mr.user.model.DUser;

public class UserBoTest {
	ApplicationContext appContext = new ClassPathXmlApplicationContext(
			"spring/config/BeanLocations.xml");
	UserBo userBo = (UserBo) appContext.getBean("userBo");
	
	@Before
	//@Test
	public void insertWordTest() {
		DUser user = new DUser();
		user.setUserName("test");
		user.setUserPassword("test");
		user.setUserEmail("test@test.ua");
		user.setAccess(2);
		userBo.save(user);
	}
	
	@Test
	public void loadUserByUsernameTest() {
		assertEquals("test", userBo.loadUserByUsername("test").getUserName());
	}
	
	@After
	//@Test
	public void deleteWordTest() {
		DUser user = (DUser) userBo.loadUserByUsername("test");
		userBo.delete(user);
	}
}
