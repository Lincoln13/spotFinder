package com.mercedes.spotfinder.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.mercedes.spotfinder.model.Geocode;
import com.mercedes.spotfinder.model.restaurant.RestaurantResponse;

@Component
public class ExternalEndPoints {
	
	Logger logger = LoggerFactory.getLogger(ExternalEndPoints.class);
	RestTemplate restTemplate = new RestTemplate();
	
	// TODO: refactoring, should get data from configuation.properties file
	public String findGeoCodes(String locationName) {
//		String url = "ExternalEndpoint.GEOCODE" + "searchtext=" + locationName +"&gen=9&apiKey=" + "Application.APIKEY";
//		logger.info("url => {}", url);
		String url = "https://geocoder.ls.hereapi.com/6.2/geocode.json?searchtext="+ locationName +"&gen=9&apiKey=mMzcnFutNmsMu-QHKOJmEpRHFFMl7sUzi7pxEn43-E4";
//		searchtext=saddffdhddgfgs&gen=9&apiKey=mMzcnFutNmsMu-QHKOJmEpRHFFMl7sUzi7pxEn43-E4
		String response = restTemplate.getForObject(url, String.class);
		logger.info("response => {}", response);
		return response;
	}
	
	public RestaurantResponse findResturants(Geocode codes) {
		RestaurantResponse response = null;
//		https://places.ls.hereapi.com/places/v1/discover/explore?at=52.5159%2C13.3777&cat=restaurant&apiKey=H6XyiCT0w1t9GgTjqhRXxDMrVj9h78ya3NuxlwM7XUs
		String longitude = codes.getLongitude();
		String lattitude = codes.getLattitude();//52.5159,13.3777
		
//		String longitude = "13.3777";
//		String lattitude = "52.5159";
		logger.info("Looking for Restaurants for {}", codes.toString());
		String url = "https://places.ls.hereapi.com/places/v1/discover/explore?at="+ lattitude +","+ longitude +"&cat=restaurant&apiKey=mMzcnFutNmsMu-QHKOJmEpRHFFMl7sUzi7pxEn43-E4";
		logger.info("url => {}", url);
		String res = restTemplate.getForObject(url, String.class);
		response = restTemplate.getForObject(url, RestaurantResponse.class);
		logger.info("response => {}", response.getSearch().getContext().getType());
//		return response;
		
		return response;
	}
}
