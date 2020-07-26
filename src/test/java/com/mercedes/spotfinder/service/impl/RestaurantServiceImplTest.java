package com.mercedes.spotfinder.service.impl;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import com.mercedes.spotfinder.dao.ExternalEndPoints;
import com.mercedes.spotfinder.model.Geocode;
import com.mercedes.spotfinder.model.App.RestaurantResponse;
import com.mercedes.spotfinder.model.external.ExtResponse;
import com.mercedes.spotfinder.testvalues.TestValues;

public class RestaurantServiceImplTest {

	private RestaurantServiceImpl service = new RestaurantServiceImpl();
	private ExternalEndPoints endpointDAO = Mockito.mock(ExternalEndPoints.class);
	
	@Before
	public void before() {
		ReflectionTestUtils.setField(service, "endpointDAO", endpointDAO);
	}
	
	@Test
	public void findRestaurantNearMeTest() throws InterruptedException, ExecutionException {
		ExtResponse response = TestValues.getTestResponse();
		
		Geocode code = TestValues.getTestGeocode();
		Mockito.when(endpointDAO.getResturants(code)).thenReturn(CompletableFuture.completedFuture(response));
		Mockito.when(endpointDAO.getParkingSpots(Mockito.any(Double[].class))).thenReturn(CompletableFuture.completedFuture(response));
		
		RestaurantResponse[] resResponse = service.findRestaurantNearMe(code);
		Assert.assertTrue(resResponse.length == 3);
		Mockito.verify(endpointDAO, Mockito.times(3)).getParkingSpots(Mockito.any(Double[].class));
	}
}
