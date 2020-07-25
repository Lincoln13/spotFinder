package com.mercedes.spotfinder.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.mercedes.spotfinder.model.Geocode;
import com.mercedes.spotfinder.model.external.ExtResponse;

@Component
public class ExternalEndPoints {
	
	Logger logger = LoggerFactory.getLogger(ExternalEndPoints.class);
	RestTemplate restTemplate = new RestTemplate();
	
	// TODO: refactoring, should get data from configuation.properties file
	public String getGeoCodes(String locationName) {

		String url = "https://geocoder.ls.hereapi.com/6.2/geocode.json?searchtext="+ locationName +"&gen=9&apiKey=mMzcnFutNmsMu-QHKOJmEpRHFFMl7sUzi7pxEn43-E4";

		String response = restTemplate.getForObject(url, String.class);
		logger.debug("response => {}", response);
		return response;
	}
	
	public ExtResponse getResturants(Geocode codes) {
		ExtResponse response = null;
		String longitude = codes.getLongitude();
		String lattitude = codes.getLattitude();
		
		logger.info("Looking for Restaurants for {}", codes.toString());
		String url = "https://places.ls.hereapi.com/places/v1/discover/explore?at="+ lattitude +","+ longitude +"&cat=restaurant&apiKey=mMzcnFutNmsMu-QHKOJmEpRHFFMl7sUzi7pxEn43-E4";
		response = restTemplate.getForObject(url, ExtResponse.class);
		
		return response;
	}

	public ExtResponse getChargingStations(Geocode codes) {
		ExtResponse response = null;
		String longitude = codes.getLongitude();
		String lattitude = codes.getLattitude();
		
		logger.info("Looking for charging stations for {}", codes.toString());
		String url = "https://places.ls.hereapi.com/places/v1/discover/search?at="+ lattitude +","+ longitude +"&q=charging&apiKey=mMzcnFutNmsMu-QHKOJmEpRHFFMl7sUzi7pxEn43-E4";
		response = restTemplate.getForObject(url, ExtResponse.class);
		
		return response;
	}
	
	public ExtResponse getParkingSpots(Double[] position) {
		ExtResponse response = null;
		String lattitude = position[0].toString();
		String longitude = position[1].toString();
		
		logger.info("Looking for Parking!");
		String url = "https://places.ls.hereapi.com/places/v1/discover/search?at="+ lattitude +","+ longitude +"&q=parking&apiKey=mMzcnFutNmsMu-QHKOJmEpRHFFMl7sUzi7pxEn43-E4";
		response = restTemplate.getForObject(url, ExtResponse.class);
		logger.info("Found {} parking spots near the place. Suggesting top - 3 near you!", response.getResults().getItems().length);
		
		return response;
		
	}
}
