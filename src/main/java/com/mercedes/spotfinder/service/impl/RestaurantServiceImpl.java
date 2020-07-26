package com.mercedes.spotfinder.service.impl;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercedes.spotfinder.dao.ExternalEndPoints;
import com.mercedes.spotfinder.helper.SortByDistance;
import com.mercedes.spotfinder.model.Geocode;
import com.mercedes.spotfinder.model.App.CommonResponse;
import com.mercedes.spotfinder.model.App.RestaurantResponse;
import com.mercedes.spotfinder.model.external.ExtResponse;
import com.mercedes.spotfinder.model.external.Items;
import com.mercedes.spotfinder.service.RestaurantService;

@Service
public class RestaurantServiceImpl extends Mapper implements RestaurantService {

	Logger logger = LoggerFactory.getLogger(RestaurantServiceImpl.class);

	@Autowired
	private ExternalEndPoints dao;

	@Override
//	@Cacheable("Restaurants")
//	@Cacheable("Restaurants")
	public RestaurantResponse[] findRestaurantNearMe(Geocode codes) {
		ExtResponse response = dao.getResturants(codes);
		logger.info("Found {} restaurants in {}", response.getResults().getItems().length, codes.getLocationName());

		Items[] items = response.getResults().getItems();

		Arrays.sort(items, new SortByDistance());

		RestaurantResponse[] restaurants = new RestaurantResponse[3];
		for (int i = 0; i < 3; i++) {
			restaurants[i] = mappingStuff(items[i]);
		}
		return restaurants;
	}

	private RestaurantResponse mappingStuff(Items item) {
		RestaurantResponse res = new RestaurantResponse();
		res.setRestaurantResponse(mappingToAppResponse(item));

		res.setParkingSpotResponse(mappingParkingSpot(res.getRestaurantResponse().getPosition()));
		return res;
	}

	private CommonResponse[] mappingParkingSpot(Double[] position) {

		ExtResponse parkingSpotResponse = dao.getParkingSpots(position);
		Items[] items = parkingSpotResponse.getResults().getItems();

		Arrays.sort(items, new SortByDistance());

		CommonResponse[] parkingSpots = new CommonResponse[3];
		for (int i = 0; i < 3; i++) {
			parkingSpots[i] = mappingToAppResponse(items[i]);
		}
		return parkingSpots;
	}
}
