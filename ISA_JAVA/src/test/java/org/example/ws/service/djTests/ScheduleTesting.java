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
public class ScheduleTesting extends WebServiceTest{

	@Before
	public void setUp(){
		super.setUp();
	}
	
	@Test																//(17) DJ
	public void testGettingScheduleForEmployee_STRING() throws Exception{
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/schedz/getForEmployee/5").accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		
		String content = result.getResponse().getContentAsString();
		printSeparator(content,"Get All Schedules for employee.");
		Assert.assertNotEquals(null, content);
	}
	
	@Test																//(18) DJ
	public void testGettingScheduleForEmployee_JSON_NOT_MANAGER() throws Exception{
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/schedz/getForEmployee/5").accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		
		String content = result.getResponse().getContentAsString();
		printSeparator(content,"Get All Schedules for employee JSON TEST CONTAINS.");
		Assert.assertNotEquals(true, content.contains("\"manages\":[1]"));
	}
	
	@Test																//(19) DJ
	public void testGettingScheduleForEmployee_LEN() throws Exception{
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/schedz/getForEmployee/5").accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		
		String content = result.getResponse().getContentAsString();
		Collection<Object> arr = this.mapFromJSON(content, Collection.class);
		printSeparator(content,"Get All Schedules for employee LENGTH.");
		Assert.assertNotEquals(0,arr.size());
	}
	
	@After
	public void kill(){
		
	}
	
}
