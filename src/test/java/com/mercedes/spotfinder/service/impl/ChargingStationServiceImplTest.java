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
import com.mercedes.spotfinder.model.App.CommonResponse;
import com.mercedes.spotfinder.model.external.ExtResponse;
import com.mercedes.spotfinder.testvalues.TestValues;

public class ChargingStationServiceImplTest {

	private ChargingStationServiceImpl service = new ChargingStationServiceImpl();
	private ExternalEndPoints endpointsDAO = Mockito.mock(ExternalEndPoints.class);

	@Before
	public void before() {
		ReflectionTestUtils.setField(service, "endpointsDAO", endpointsDAO);
	}
	
	@Test
	public void findChargingStationsNearMeTest() throws InterruptedException, ExecutionException {
		ExtResponse response = TestValues.getTestResponse();
		
		Geocode code = TestValues.getTestGeocode();
		Mockito.when(endpointsDAO.getChargingStations(code)).thenReturn(CompletableFuture.completedFuture(response));
		
		CommonResponse[] resResponse = service.findChargingStationsNearMe(code);
		Assert.assertTrue(resResponse.length == 3);
		Mockito.verify(endpointsDAO, Mockito.times(1)).getChargingStations(code);
	}
	
}
