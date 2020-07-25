package com.mercedes.spotfinder.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercedes.spotfinder.controller.SpotFinderRestResource;
import com.mercedes.spotfinder.dao.ExternalEndPoints;
import com.mercedes.spotfinder.model.Geocode;
import com.mercedes.spotfinder.model.restaurant.RestaurantResponse;
import com.mercedes.spotfinder.service.RestaurantService;

@Service
public class RestaurantServiceImpl implements RestaurantService{
	
	Logger logger = LoggerFactory.getLogger(SpotFinderRestResource.class);
	
	@Autowired
	private ExternalEndPoints dao;
	
	@Override
	public void findRestaurant(Geocode codes) {
		RestaurantResponse response = dao.findResturants(codes);
		logger.info("response => {}", response.getResults().getItems().length);
	}
	
}
