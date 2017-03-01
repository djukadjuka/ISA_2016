package org.example.ws.service;

import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Before;

@Transactional
public class ReservationTestService extends WebServiceTest{
		

	@Before
	public void setUp(){
		super.setUp();
	}
	
	@After
	public void kill(){
		
	}
}
