package com.mercedes.spotfinder.service.impl;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercedes.spotfinder.controller.SpotFinderRestResource;
import com.mercedes.spotfinder.dao.ExternalEndPoints;
import com.mercedes.spotfinder.helper.SortByDistance;
import com.mercedes.spotfinder.model.Geocode;
import com.mercedes.spotfinder.model.App.AppResponse;
import com.mercedes.spotfinder.model.App.AppRestaurantResponse;
import com.mercedes.spotfinder.model.restaurant.Items;
import com.mercedes.spotfinder.model.restaurant.RestaurantResponse;
import com.mercedes.spotfinder.service.RestaurantService;

@Service
public class RestaurantServiceImpl implements RestaurantService{
	
	Logger logger = LoggerFactory.getLogger(SpotFinderRestResource.class);
	
	@Autowired
	private ExternalEndPoints dao;
	
	@Override
	public AppRestaurantResponse[] findRestaurant(Geocode codes) {
		RestaurantResponse response = dao.findResturants(codes);
		logger.info("Found {} restaurants in {}", response.getResults().getItems().length, codes.getLocationName());
		
		Items[] items = response.getResults().getItems();
		
		Arrays.sort(items, new SortByDistance()); 
		
		AppResponse appResponse = new AppResponse();
		AppRestaurantResponse[] restaurants = new AppRestaurantResponse[3];
		for (int i = 0; i < 3; i ++ ) {
			restaurants[i] = mapping(items[i]);
		}
		appResponse.setRestaurant(restaurants);
		return restaurants;
	}
	
	private AppRestaurantResponse mapping(Items item) {
		AppRestaurantResponse res = new AppRestaurantResponse();
		res.setName(item.getTitle());
		res.setCategory(item.getCategory().getTitle());
		res.setAddress(item.getVicinity());
		res.setDistance(item.getDistance());
		res.setMoreInfoRef(item.getHref());
		res.setPosition(item.getPosition());
		res.setTags(item.getTags());
		logger.info("{}",res.toString());
		return res;
	}
	
}
