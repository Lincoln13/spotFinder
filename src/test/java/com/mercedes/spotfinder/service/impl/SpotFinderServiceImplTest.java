package com.mercedes.spotfinder.service.impl;

import java.util.concurrent.ExecutionException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import com.mercedes.spotfinder.cache.service.CacheService;
import com.mercedes.spotfinder.exception.BusinessException;
import com.mercedes.spotfinder.exception.ProcessingException;
import com.mercedes.spotfinder.model.Geocode;
import com.mercedes.spotfinder.model.App.AppResponse;
import com.mercedes.spotfinder.model.App.CommonResponse;
import com.mercedes.spotfinder.model.App.RestaurantResponse;
import com.mercedes.spotfinder.model.cache.CacheDataStore;
import com.mercedes.spotfinder.service.ChargingStationService;
import com.mercedes.spotfinder.service.GeocodesService;
import com.mercedes.spotfinder.service.RestaurantService;

public class SpotFinderServiceImplTest {

	private SpotFinderServiceImpl service = new SpotFinderServiceImpl();

	private CacheService cacheService = Mockito.mock(CacheService.class);
	private GeocodesService codeService = Mockito.mock(GeocodesService.class);
	private RestaurantService restaurantService = Mockito.mock(RestaurantService.class);
	private ChargingStationService chargingStationService = Mockito.mock(ChargingStationService.class);

	@Before
	public void before() throws BusinessException, ProcessingException, InterruptedException, ExecutionException {
		RestaurantResponse[] restaurant = new RestaurantResponse[3];
		CommonResponse[] chargingStation = new CommonResponse[3];

		ReflectionTestUtils.setField(service, "cacheService", cacheService);
		ReflectionTestUtils.setField(service, "codeService", codeService);
		ReflectionTestUtils.setField(service, "restaurantService", restaurantService);
		ReflectionTestUtils.setField(service, "chargingStationService", chargingStationService);

		Mockito.when(codeService.findGeocode(Mockito.any())).thenReturn(new Geocode("test", "34", "-2"));
		Mockito.when(restaurantService.findRestaurantNearMe(Mockito.any(Geocode.class))).thenReturn(restaurant);
		Mockito.when(chargingStationService.findChargingStationsNearMe(Mockito.any(Geocode.class)))
				.thenReturn(chargingStation);
		
	}

	@Test
	public void testFindAllThings_1()
			throws BusinessException, ProcessingException, InterruptedException, ExecutionException {

		Mockito.when(cacheService.findFromCache(Mockito.any())).thenReturn(null);
		
		String locationName = "test";
		AppResponse response = service.findAllThings(locationName);
		Assert.assertTrue(response.getLocation().equals(locationName));
		Mockito.verify(cacheService, Mockito.times(1)).storeToCache(Mockito.any(), Mockito.any());
	}
	
	@Test
	public void testFindAllThings_2() throws ProcessingException, BusinessException, InterruptedException, ExecutionException {
		CacheDataStore cache = new CacheDataStore();
		cache.setRestaurant(null);
		cache.setChargingStations(null);
		Mockito.when(cacheService.findFromCache(Mockito.any())).thenReturn(cache);
		
		String locationName = "test";
		AppResponse response = service.findAllThings(locationName);
		Assert.assertTrue(response.getLocation().equals(locationName));
		Assert.assertTrue(response.getRestaurant() == null);
		Assert.assertTrue(response.getChargingStations() == null);
		Mockito.verify(cacheService, Mockito.times(0)).storeToCache(Mockito.any(), Mockito.any());
	
	}
}
