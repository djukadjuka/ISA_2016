package org.example.ws.service;

import java.util.HashSet;
import java.util.LinkedHashMap;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.EmployeeBean;



@Transactional
public class EmployeeTestService extends WebServiceTest{

	@Before
	public void setUp(){
		super.setUp();
	}
	
	private void printSeparator(String printMe,String title){
		System.out.println("---------------------------"+title+"\n\n\n");
		System.out.println(printMe);
		System.out.println("\n\n\n---------------------------");
	}
	
	@Test																				//(1)
	public void testFindOneEmployee() throws Exception{
		
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/EmployeeControler/getEmployeeById/1").accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		
		String content = result.getResponse().getContentAsString();
		printSeparator(content,"Find one Employee");
		Assert.assertNotNull(content);
	}
	
	@Test																				//(2)
	public void testGettingEmployeesForCertainRestaurant() throws Exception{
		MvcResult result = mvc.perform(
								MockMvcRequestBuilders.get(
										"/EmployeeControler/getEmployeesForRestaurant/1")
											.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		
		String content = result.getResponse().getContentAsString();
		printSeparator(content,"Find Employees For Restaurant");
		Assert.assertNotNull(content);
		
	}
	
	@Test																				//(3)
	public void getOnlyWaitersForRestaurant() throws Exception{
		MvcResult result = mvc.perform(
				MockMvcRequestBuilders.get(
						"/EmployeeControler/getWaitersForRestaurant/1")
							.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		String content = result.getResponse().getContentAsString();
		
		HashSet<EmployeeBean> arr = this.mapFromJSON(content, HashSet.class);
		System.out.println("---------------------------------\n\n");
		for(Object emp : arr){
			System.out.println(emp);
		}
		System.out.println("\n\n-------------------------------");
		
		printSeparator(content,"Find WAITERS for restaurant");
		Assert.assertNotNull(content);
	}
	
	@After
	public void kill(){
		
	}
	
}
