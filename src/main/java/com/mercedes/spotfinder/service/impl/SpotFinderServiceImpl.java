package com.mercedes.spotfinder.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercedes.spotfinder.exception.BusinessException;
import com.mercedes.spotfinder.exception.ProcessingException;
import com.mercedes.spotfinder.model.Geocode;
import com.mercedes.spotfinder.model.App.AppResponse;
import com.mercedes.spotfinder.service.ChargingStationService;
import com.mercedes.spotfinder.service.GeocodesService;
import com.mercedes.spotfinder.service.RestaurantService;
import com.mercedes.spotfinder.service.SpotFinderService;

@Service
public class SpotFinderServiceImpl implements SpotFinderService {

	Logger logger = LoggerFactory.getLogger(SpotFinderServiceImpl.class);

	@Autowired
	private GeocodesService codeService;
	@Autowired
	private RestaurantService restaurantService;
	@Autowired
	private ChargingStationService chargingStationService;

	@Override
	public AppResponse findAllThings(String locationName) throws ProcessingException, BusinessException {
		
		AppResponse appResponse = new AppResponse();
		
		appResponse.setLocation(locationName);
		Geocode codes = codeService.findGeocode(locationName);
		logger.info("Coordinates for {}", codes.toString());
		
		appResponse.setRestaurant(restaurantService.findRestaurantNearMe(codes));
		appResponse.setChargingStations(chargingStationService.findChargingStationsNearMe(codes));
		
		return appResponse;
	}
}
