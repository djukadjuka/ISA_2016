package org.example.ws.service;

import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@Transactional
public class UserTestService extends WebServiceTest{
	

	@Before
	public void setUp(){
		super.setUp();
	}
	
	// SKOS 1
	
	@Test																				
	public void skosTestGetUser() throws Exception{
		
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/getUser/1").accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		
		String content = result.getResponse().getContentAsString();
		printSeparator(content,"Find one user");
		Assert.assertNotNull(content);
	}
	
	// SKOS 2
	
	@Test																			
	public void skosTestCheckAvailableUsername() throws Exception{
		MvcResult result = mvc.perform(
								MockMvcRequestBuilders.get(
										"/checkUsername/ciganijaaa/1")
											.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		
		String content = result.getResponse().getContentAsString();
		printSeparator(content,"Check username availability");
		Assert.assertNotNull(content);
	}
	
	// SKOS 3
	
	@Test																			
	public void skosTestGetAllUsers() throws Exception{
		MvcResult result = mvc.perform(
								MockMvcRequestBuilders.get(
										"/getAllUsers/1")
											.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		
		String content = result.getResponse().getContentAsString();
		printSeparator(content,"Find all users that are not my friends");
		Assert.assertNotNull(content);
	}
	
	// SKOS 4
	
	@Test																			
	public void skosTestGetPendingDeliverers() throws Exception{
		MvcResult result = mvc.perform(
								MockMvcRequestBuilders.get(
										"/userRepo/getPendingDeliverers")
											.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		
		String content = result.getResponse().getContentAsString();
		printSeparator(content,"Find all pending deliverers");
		Assert.assertNotNull(content);
	}
	
	@After
	public void kill(){
		
	}
	
}
