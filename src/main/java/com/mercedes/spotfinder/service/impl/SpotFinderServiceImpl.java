package com.mercedes.spotfinder.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mercedes.spotfinder.exception.BusinessException;
import com.mercedes.spotfinder.model.Geocode;
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
	
	@Override
	public void findAllThings(String locationName) {
		try {
			Geocode codes = codeService.findGeocode(locationName);
			logger.info("Coordinates for {}", codes.toString());
			restaurantService.findRestaurant(codes);
		} catch (JsonProcessingException | BusinessException e) {
			e.printStackTrace();
		}
	}
}
