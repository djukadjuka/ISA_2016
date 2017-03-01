package org.example.ws.service.djTests;

import java.util.Collection;

import org.example.ws.service.WebServiceTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class ProductTesting extends WebServiceTest{

	@Before
	public void setUp(){
		super.setUp();
	}
	
	@Test																//(9) DJ
	public void testGettingAllProducts() throws Exception{
		///getAllProducts
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/getAllProducts").accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		
		String content = result.getResponse().getContentAsString();
		printSeparator(content,"Get All Possible Products");
		Assert.assertNotNull(content);
	}
	
	@Test																//(10) DJ
	public void testCheckingProducts() throws Exception{
		///getAllProducts
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/getAllProducts").accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		
		String content = result.getResponse().getContentAsString();
		Collection<Object> arr = this.mapFromJSON(content, Collection.class);
		printSeparator(content,"Get All Possible Products");
		Assert.assertNotEquals(0, arr.size());
	}
	
	@Test																//(11) DJ
	public void testGettingAllDrinks() throws Exception{
		//"/getAllDrinks"
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/getAllDrinks").accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		
		String content = result.getResponse().getContentAsString();
		Collection<Object> arr = this.mapFromJSON(content, Collection.class);
		printSeparator(content,"Get All Drinks");
		Assert.assertNotEquals(0, arr.size());
	}
	
	@Test																//(12) DJ
	public void testGettingAllDrinksJSON() throws Exception{
		//"/getAllDrinks"
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/getAllDrinks").accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		
		String content = result.getResponse().getContentAsString();
		printSeparator(content,"Get All Drinks JSON");
		Assert.assertNotNull(content);
	}
	
	@Test																//(13) DJ
	public void testContainsFoodInDrinks() throws Exception{
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/getAllDrinks").accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		
		String content = result.getResponse().getContentAsString();
		printSeparator(content,"Get All Drinks NOT EQUALS");
		Assert.assertNotEquals(true, content.contains("\"food\":true"));
	}
	
	@Test																//(14) DJ
	public void testGettingAllFood() throws Exception{
		//"/getAllDrinks"
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/getAllFood").accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		
		String content = result.getResponse().getContentAsString();
		Collection<Object> arr = this.mapFromJSON(content, Collection.class);
		printSeparator(content,"Get All Drinks");
		Assert.assertNotEquals(0, arr.size());
	}
	
	@Test																//(15) DJ
	public void testGettingAllFoodJSON() throws Exception{
		//"/getAllDrinks"
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/getAllFood").accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		
		String content = result.getResponse().getContentAsString();
		printSeparator(content,"Get All Drinks JSON");
		Assert.assertNotNull(content);
	}
	
	@Test																//(16) DJ
	public void testContainsDrinksInFood() throws Exception{
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/getAllFood").accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		
		String content = result.getResponse().getContentAsString();
		printSeparator(content,"Get All Drinks NOT EQUALS");
		Assert.assertNotEquals(true, content.contains("\"food\":false"));
	}
	
	
	
	@After
	public void kill(){
		
	}
	
}
