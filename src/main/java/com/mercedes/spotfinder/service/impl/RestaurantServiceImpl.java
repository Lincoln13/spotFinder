package com.mercedes.spotfinder.service.impl;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercedes.spotfinder.dao.ExternalEndPoints;
import com.mercedes.spotfinder.helper.SortByDistance;
import com.mercedes.spotfinder.model.Geocode;
import com.mercedes.spotfinder.model.App.RestaurantResponse;
import com.mercedes.spotfinder.model.external.Items;
import com.mercedes.spotfinder.model.external.ExtResponse;
import com.mercedes.spotfinder.service.RestaurantService;

@Service
public class RestaurantServiceImpl implements RestaurantService{
	
	Logger logger = LoggerFactory.getLogger(RestaurantServiceImpl.class);
	
	@Autowired
	private ExternalEndPoints dao;
	
	@Override
	public RestaurantResponse[] findRestaurantNearMe(Geocode codes) {
		ExtResponse response = dao.getResturants(codes);
		logger.info("Found {} restaurants in {}", response.getResults().getItems().length, codes.getLocationName());
		
		Items[] items = response.getResults().getItems();
		
		Arrays.sort(items, new SortByDistance()); 
		
		RestaurantResponse[] restaurants = new RestaurantResponse[3];
		for (int i = 0; i < 3; i ++ ) {
			restaurants[i] = mapping(items[i]);
		}
		return restaurants;
	}
	
	private RestaurantResponse mapping(Items item) {
		RestaurantResponse res = new RestaurantResponse();
		res.setName(item.getTitle());
		res.setCategory(item.getCategory().getTitle());
		res.setAddress(item.getVicinity());
		res.setDistance(item.getDistance());
		res.setMoreInfoRef(item.getHref());
		res.setPosition(item.getPosition());
		res.setTags(item.getTags());
		res.setOpeningHours(item.getOpeningHours());
		res.setAlternativeNames(item.getAlternativeNames());
		logger.info("{}",res.toString());
		return res;
	}
	
}
