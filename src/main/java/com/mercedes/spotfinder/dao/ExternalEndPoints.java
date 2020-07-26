package com.mercedes.spotfinder.dao;

import java.text.MessageFormat;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.mercedes.spotfinder.appconstants.AppConstants;
import com.mercedes.spotfinder.model.Geocode;
import com.mercedes.spotfinder.model.external.ExtResponse;

/**
 * DAO (data access object) class to fetch data 
 * from various endpoints. 
 *
 */
@Component
public class ExternalEndPoints {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ExternalEndPoints.class);
	private RestTemplate restTemplate = new RestTemplateBuilder().build();
	
	/**
	 * Returns coordinate of a location
	 * @param locationName : user entered value
	 * @return String: JSON string with coordinates of a location
	 */
	public String getGeoCodes(String locationName) {
		String url = MessageFormat.format(AppConstants.ENDPOINT_GEOCODE, locationName, AppConstants.API_KEY);
		return restTemplate.getForObject(url, String.class);
	}
	
	/**
	 * Returns Restaurants near to the location
	 * @param codes : Object holds details about location
	 * @return ExtResponse : response obtained from endpoint
	 */
	@Async
	public CompletableFuture<ExtResponse> getResturants(Geocode codes) {
		ExtResponse response = null;
		String longitude = codes.getLongitude();
		String lattitude = codes.getLattitude();
		
		LOGGER.info("Looking for Restaurants for {}", codes.toString());
		response = callExternalEndpoint(AppConstants.ENDPOINT_RESTAURANT, lattitude, longitude);
		return CompletableFuture.completedFuture(response);
	}

	/**
	 * Returns Charging stations near to the location
	 * @param codes : Object holds details about location
	 * @return ExtResponse : response obtained from endpoint
	 */
	@Async
	public CompletableFuture<ExtResponse> getChargingStations(Geocode codes) {
		ExtResponse response = null;
		String longitude = codes.getLongitude();
		String lattitude = codes.getLattitude();
		
		LOGGER.info("Looking for charging stations for {}", codes.toString());
		response = callExternalEndpoint(AppConstants.ENDPOINT_STATIONS, lattitude, longitude);
		return CompletableFuture.completedFuture(response);
	}
	
	/**
	 * Returns Parking spots near to the location
	 * @param codes : Object holds details about location
	 * @return ExtResponse : response obtained from endpoint
	 */
	@Async
	public CompletableFuture<ExtResponse> getParkingSpots(Double[] position) {
		ExtResponse response = null;
		String lattitude = position[0].toString();
		String longitude = position[1].toString();
		
		LOGGER.info("Looking for Parking!");
		response = callExternalEndpoint(AppConstants.ENDPOINT_PARKING, lattitude, longitude);
		LOGGER.info("Found {} parking spots near the place. Suggesting top - 3 near you!", response.getResults().getItems().length);
		return CompletableFuture.completedFuture(response);
	}
	
	/*
	 * common method to build url and call the endpoints
	 */
	private ExtResponse callExternalEndpoint(String endpoint, String lattitude, String longitude) {
		String url = MessageFormat.format(endpoint, lattitude, longitude, AppConstants.API_KEY);
		ExtResponse response = restTemplate.getForObject(url, ExtResponse.class);
		return response;
	}
}
