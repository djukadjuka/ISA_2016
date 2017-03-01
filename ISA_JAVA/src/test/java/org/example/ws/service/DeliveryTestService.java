package org.example.ws.service;

import java.util.Collection;
import java.util.HashSet;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.EmployeeBean;
import com.example.domain.deliveryBeans.DeliveryOrderBean;
import com.example.domain.deliveryBeans.DeliveryOrderBid;
import com.example.domain.deliveryBeans.DeliveryOrderItem;
import com.example.service.deliveryServices.DeliveryBidService;
import com.example.service.deliveryServices.DeliveryBidServiceBean;
import com.example.service.deliveryServices.DeliveryItemService;
import com.example.service.deliveryServices.DeliveryItemServiceBean;
import com.example.service.deliveryServices.DeliveryOrderService;
import com.example.service.deliveryServices.DeliveryOrderServiceBean;

@Transactional
public class DeliveryTestService extends WebServiceTest{

	@Autowired
	private DeliveryOrderService delivery_order_service = new DeliveryOrderServiceBean();
	
	@Autowired
	private DeliveryItemService delivery_item_service = new DeliveryItemServiceBean();
	
	@Autowired
	private DeliveryBidService delivery_bid_service = new DeliveryBidServiceBean();
	
	@Before
	public void setUp(){
		super.setUp();
	}
		
	@Test																//(4) DJ
	public void testGettingOrders(){
		
		Collection<DeliveryOrderBean> orders = delivery_order_service.findAll();
		
		Assert.assertNotNull(orders);
		
		Assert.assertNotEquals(0, orders.size());
	}
	
	@Test																		//(5) DJ
	public void testGettingItems(){
		Collection<DeliveryOrderItem> items = delivery_item_service.findAll();
		Assert.assertNotNull(items);
		
		Assert.assertNotEquals(0, items.size());
	}
	
	@Test																		//(6) DJ
	public void testGettingBid(){
		Collection<DeliveryOrderBid> bids = delivery_bid_service.findAll();
		Assert.assertNotNull(bids);
		
		Assert.assertNotEquals(0, bids.size());
	}
	
	@Test																		//(7) DJ
	public void testGettingStartingData() throws Exception{
		
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/delivery_controller/getStartingData/1").accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		
		String content = result.getResponse().getContentAsString();
		printSeparator(content,"Getting beginning data for restaurant deliveries.");
		Assert.assertNotNull(content);
	}
	
	@Test
	public void getAllBidsForDelivery() throws Exception{
		///delivery_controller/getBidsForDeliveryId/{ord_id}
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/delivery_controller/getBidsForDeliveryId/1").accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		
		String content = result.getResponse().getContentAsString();
		Collection<Object> arr = this.mapFromJSON(content, Collection.class);
		printSeparator(content,"Getting beginning data for restaurant deliveries.");
		Assert.assertNotEquals(0,arr.size());
	}
	
	@After
	public void kill(){
		
	}
	
}
