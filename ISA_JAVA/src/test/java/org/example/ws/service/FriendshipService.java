package org.example.ws.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class FriendshipService extends WebServiceTest{
	

	@Before
	public void setUp(){
		super.setUp();
	}
	
	@After
	public void kill(){
		
	}
	
	//SKOS 5
	
	@Test																			
	public void skosTestGetFriendship() throws Exception{
		MvcResult result = mvc.perform(
								MockMvcRequestBuilders.get(
										"/getFriendships/1")
											.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		
		String content = result.getResponse().getContentAsString();
		printSeparator(content,"Get friendship with id 1");
		Assert.assertNotNull(content);
	}
	
	
	//SKOS 6

	@Test																			
	public void skosTestGetFriendRequest() throws Exception{
		MvcResult result = mvc.perform(
								MockMvcRequestBuilders.get(
										"/getFriendRequests/50")
											.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		
		String content = result.getResponse().getContentAsString();
		printSeparator(content,"Get friend request with id 50");
		Assert.assertNull(content);
	}
}
