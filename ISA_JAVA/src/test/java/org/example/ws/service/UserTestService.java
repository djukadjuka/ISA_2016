package org.example.ws.service;

import java.util.Collection;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.UserBean;
import com.example.service.UserService;
import com.example.service.UserServiceBean;

@Transactional
public class UserTestService extends WebServiceTest{

	@Autowired
	private UserService user_service = new UserServiceBean();
	
	@Before
	public void setUp(){
		super.setUp();
	}
	
	@Test
	public void testFindAll() throws Exception{
		
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/EmployeeControler/getEmployeeById/1").accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		
		String content = result.getResponse().getContentAsString();
		System.out.println(content);
		Assert.assertNotNull(content);
	}
	
	@After
	public void kill(){
		
	}
	
}
