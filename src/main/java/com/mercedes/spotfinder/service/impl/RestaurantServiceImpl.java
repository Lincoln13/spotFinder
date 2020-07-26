package com.mercedes.spotfinder.service.impl;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;

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

	private static final Logger LOGGER = LoggerFactory.getLogger(RestaurantServiceImpl.class);

	@Autowired
	private ExternalEndPoints endpointDAO;

	/**
	 * Service class to find the top - 3 restaurants nearer to the location
	 * Also, finds top -3 parking spots closer to each restaurants.
	 * so, overall 3 - restaurants and 9 parking spots.
	 */
	@Override
	public RestaurantResponse[] findRestaurantNearMe(Geocode codes) throws InterruptedException, ExecutionException {
		ExtResponse response = endpointDAO.getResturants(codes).get();
		LOGGER.info("Found {} restaurants in {}", response.getResults().getItems().length, codes.getLocationName());

		Items[] items = response.getResults().getItems();

		Arrays.sort(items, new SortByDistance());

		RestaurantResponse[] restaurants = new RestaurantResponse[3];
		for (int i = 0; i < 3; i++) {
			restaurants[i] = mappingRestaurant(items[i]);
		}
		return restaurants;
	}

	// creates restaurant response
	private RestaurantResponse mappingRestaurant(Items item) throws InterruptedException, ExecutionException {
		RestaurantResponse res = new RestaurantResponse();
		res.setRestaurantResponse(mappingToAppResponse(item));

		// call to create parking response
		res.setParkingSpotResponse(mappingParkingSpot(res.getRestaurantResponse().getPosition()));
		return res;
	}

	// creates parking response
	private CommonResponse[] mappingParkingSpot(Double[] position) throws InterruptedException, ExecutionException {

		ExtResponse parkingSpotResponse = endpointDAO.getParkingSpots(position).get();
		Items[] items = parkingSpotResponse.getResults().getItems();

		Arrays.sort(items, new SortByDistance());

		CommonResponse[] parkingSpots = new CommonResponse[3];
		for (int i = 0; i < 3; i++) {
			parkingSpots[i] = mappingToAppResponse(items[i]);
		}
		return parkingSpots;
	}
}
