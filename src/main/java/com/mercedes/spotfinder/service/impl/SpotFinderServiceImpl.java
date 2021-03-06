package com.mercedes.spotfinder.service.impl;

import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.mercedes.spotfinder.service.SpotFinderService;

@Service
public class SpotFinderServiceImpl implements SpotFinderService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SpotFinderServiceImpl.class);

	@Autowired
	private CacheService cacheService;
	@Autowired
	private GeocodesService codeService;
	@Autowired
	private RestaurantService restaurantService;
	@Autowired
	private ChargingStationService chargingStationService;

	/**
	 * Main service class which calls other service class and return 
	 * app response back to controller.
	 */
	@Override
	public AppResponse findAllThings(String locationName) throws ProcessingException, BusinessException, InterruptedException, ExecutionException {
		
		AppResponse appResponse = new AppResponse();
		appResponse.setLocation(locationName);
		
		// Looking into Cache to find the data
		CacheDataStore cache = cacheService.findFromCache(locationName);
		if (cache != null) {
			LOGGER.info("{} found in Cache", locationName);
			return mapToAppResponse(appResponse, cache.getRestaurant(), cache.getChargingStations());
		}
		
		Geocode codes = codeService.findGeocode(locationName);
		appResponse = mapToAppResponse(appResponse, restaurantService.findRestaurantNearMe(codes), chargingStationService.findChargingStationsNearMe(codes));
		
		// Storing the response to cache
		cacheService.storeToCache(locationName, appResponse);
		
		return appResponse;
	}
	
	// maps responses to application specific response object.
	private AppResponse mapToAppResponse(AppResponse response, RestaurantResponse[] restaurant, CommonResponse[] chargingStation) {
		response.setRestaurant(restaurant);
		response.setChargingStations(chargingStation);
		return response;
	}
}
