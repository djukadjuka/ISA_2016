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
public class TableTestService extends WebServiceTest{
	
	@Before
	public void setUp(){
		super.setUp();
	}
	
	@After
	public void kill(){
		
	}
	
	@Test																			
	public void skosTestGetAllTables() throws Exception{
		MvcResult result = mvc.perform(
								MockMvcRequestBuilders.get(
										"/getAllTables/1/51212512/521512512")
											.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		
		String content = result.getResponse().getContentAsString();
		printSeparator(content,"Get all tables from zone");
		Assert.assertNotNull(content);
	}
	
	@Test																			
	public void skosTestGetTablesForRestaurant() throws Exception{
		MvcResult result = mvc.perform(
								MockMvcRequestBuilders.get(
										"/tableController/getTablesForRestaurant/1")
											.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		
		String content = result.getResponse().getContentAsString();
		printSeparator(content,"Get all tables from zone");
		Assert.assertNotNull(content);
	}
}
