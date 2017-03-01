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
	public void testGetUser() throws Exception{
		
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/getUser/1").accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		
		String content = result.getResponse().getContentAsString();
		printSeparator(content,"Find one user");
		Assert.assertNotNull(content);
	}
	
	// SKOS 2
	
	@Test																			
	public void testCheckAvailableUsername() throws Exception{
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
		public void testCheckAvailableUsername() throws Exception{
			MvcResult result = mvc.perform(
									MockMvcRequestBuilders.get(
											"/checkUsername/ciganijaaa/1")
												.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
			
			String content = result.getResponse().getContentAsString();
			printSeparator(content,"Check username availability");
			Assert.assertNotNull(content);
		}
	
	@After
	public void kill(){
		
	}
	
}
