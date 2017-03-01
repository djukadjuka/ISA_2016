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
public class ReservationTestService extends WebServiceTest{
		

	@Before
	public void setUp(){
		super.setUp();
	}
	
	@After
	public void kill(){
		
	}
	
	//SKOS 7

		@Test																			
		public void skosTestGetReservationsForOriginator() throws Exception{
			MvcResult result = mvc.perform(
									MockMvcRequestBuilders.get(
											"/getReservationsForOriginator/1")
												.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
			
			String content = result.getResponse().getContentAsString();
			printSeparator(content,"Get reservations for originator 1");
			Assert.assertNotNull(content);
		}
		

	//SKOS 8

		@Test																			
		public void skosTestGetReservationsForRecipient() throws Exception{
			MvcResult result = mvc.perform(
									MockMvcRequestBuilders.get(
											"/getReservationsForRecipient/2")
												.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
			
			String content = result.getResponse().getContentAsString();
			printSeparator(content,"Get reservations for recipient 2");
			Assert.assertNotNull(content);
		}
		
	
		@Test																			
		public void skosTestInviteData() throws Exception{
			MvcResult result = mvc.perform(
									MockMvcRequestBuilders.get(
											"/inviteData/505050")
												.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
			
			String content = result.getResponse().getContentAsString();
			printSeparator(content,"Get invite data");
			Assert.assertNull(content);
		}
		
		@Test																			
		public void skosTestGetRestaurantVisitHistory() throws Exception{
			MvcResult result = mvc.perform(
									MockMvcRequestBuilders.get(
											"/getRestaurantVisitHistory/1")
												.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
			
			String content = result.getResponse().getContentAsString();
			printSeparator(content,"Get reservation visit history");
			Assert.assertNotNull(content);
		}
		
		@Test																			
		public void skosTestgetRestaurantAverageRateAll() throws Exception{
			MvcResult result = mvc.perform(
									MockMvcRequestBuilders.get(
											"/getRestaurantAverageRateAll/1")
												.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
			
			String content = result.getResponse().getContentAsString();
			printSeparator(content,"Get restaurants average rate for all");
			Assert.assertNotNull(content);
		}
		
		@Test																			
		public void skosTestgetRestaurantAverageRateMe() throws Exception{
			MvcResult result = mvc.perform(
									MockMvcRequestBuilders.get(
											"/getRestaurantAverageRateMe/1")
												.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
			
			String content = result.getResponse().getContentAsString();
			printSeparator(content,"Get restaurants average rate for me");
			Assert.assertNotNull(content);
		}
		
		@Test																			
		public void skosTestgetRestaurantAverageRateFriends() throws Exception{
			MvcResult result = mvc.perform(
									MockMvcRequestBuilders.get(
											"/getRestaurantAverageRateFriends/1")
												.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
			
			String content = result.getResponse().getContentAsString();
			printSeparator(content,"Get restaurants average rate for friends");
			Assert.assertNotNull(content);
		}
		
		@Test																			
		public void skosTestgetFoodAverageRateAll() throws Exception{
			MvcResult result = mvc.perform(
									MockMvcRequestBuilders.get(
											"/getFoodAverageRateAll/1")
												.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
			
			String content = result.getResponse().getContentAsString();
			printSeparator(content,"Get restaurants average rate for all");
			Assert.assertNotNull(content);
		}
		
		@Test																			
		public void skosTestgetFoodAverageRateMe() throws Exception{
			MvcResult result = mvc.perform(
									MockMvcRequestBuilders.get(
											"/getFoodAverageRateMe/1")
												.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
			
			String content = result.getResponse().getContentAsString();
			printSeparator(content,"Get restaurants food average rate for me");
			Assert.assertNotNull(content);
		}
		
		@Test																			
		public void skosTestgetFoodAverageRateFriends() throws Exception{
			MvcResult result = mvc.perform(
									MockMvcRequestBuilders.get(
											"/getFoodAverageRateFriends/1")
												.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
			
			String content = result.getResponse().getContentAsString();
			printSeparator(content,"Get food for restaurant average rate friends");
			Assert.assertNotNull(content);
		}
		
		@Test																			
		public void skosTestgetWaiterAverageRateAll() throws Exception{
			MvcResult result = mvc.perform(
									MockMvcRequestBuilders.get(
											"/getWaiterAverageRateAll/1")
												.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
			
			String content = result.getResponse().getContentAsString();
			printSeparator(content,"Get restaurants waiter average rate for all");
			Assert.assertNotNull(content);
		}
		
		@Test																			
		public void skosTestgetWaiterAverageRateMe() throws Exception{
			MvcResult result = mvc.perform(
									MockMvcRequestBuilders.get(
											"/getWaiterAverageRateMe/1")
												.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
			
			String content = result.getResponse().getContentAsString();
			printSeparator(content,"Get restaurants waiter average rate for me");
			Assert.assertNotNull(content);
		}
		
		@Test																			
		public void skosTestgetWaiterAverageRateFriends() throws Exception{
			MvcResult result = mvc.perform(
									MockMvcRequestBuilders.get(
											"/getWaiterAverageRateFriends/1")
												.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
			
			String content = result.getResponse().getContentAsString();
			printSeparator(content,"Get waiter for restaurant average rate friends");
			Assert.assertNotNull(content);
		}
		
}
