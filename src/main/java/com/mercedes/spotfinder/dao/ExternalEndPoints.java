package com.mercedes.spotfinder.dao;

import java.text.MessageFormat;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.mercedes.spotfinder.appconstants.AppConstants;
import com.mercedes.spotfinder.model.Geocode;
import com.mercedes.spotfinder.model.external.ExtResponse;

@Component
public class ExternalEndPoints {
	
	Logger logger = LoggerFactory.getLogger(ExternalEndPoints.class);
	RestTemplate restTemplate = new RestTemplate();
	
	public String getGeoCodes(String locationName) {

		String url = MessageFormat.format(AppConstants.ENDPOINT_GEOCODE, locationName, AppConstants.API_KEY);
		
		String response = restTemplate.getForObject(url, String.class);
		return response;
	}
	
	@Async
	public CompletableFuture<ExtResponse> getResturants(Geocode codes) {
		ExtResponse response = null;
		String longitude = codes.getLongitude();
		String lattitude = codes.getLattitude();
		
		logger.info("Looking for Restaurants for {}", codes.toString());
		String url = MessageFormat.format(AppConstants.ENDPOINT_RESTAURANT, lattitude, longitude, AppConstants.API_KEY);
		
		response = restTemplate.getForObject(url, ExtResponse.class);
		return CompletableFuture.completedFuture(response);
	}

	@Async
	public CompletableFuture<ExtResponse> getChargingStations(Geocode codes) {
		ExtResponse response = null;
		String longitude = codes.getLongitude();
		String lattitude = codes.getLattitude();
		
		logger.info("Looking for charging stations for {}", codes.toString());
		String url = MessageFormat.format(AppConstants.ENDPOINT_STATIONS, lattitude, longitude, AppConstants.API_KEY);
		
		response = restTemplate.getForObject(url, ExtResponse.class);
		return CompletableFuture.completedFuture(response);
	}
	
	@Async
	public CompletableFuture<ExtResponse> getParkingSpots(Double[] position) {
		ExtResponse response = null;
		String lattitude = position[0].toString();
		String longitude = position[1].toString();
		
		logger.info("Looking for Parking!");
		String url = MessageFormat.format(AppConstants.ENDPOINT_PARKING, lattitude, longitude, AppConstants.API_KEY);

		response = restTemplate.getForObject(url, ExtResponse.class);
		logger.info("Found {} parking spots near the place. Suggesting top - 3 near you!", response.getResults().getItems().length);
		return CompletableFuture.completedFuture(response);
	}
}
