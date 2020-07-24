package com.mercedes.spotfinder.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

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
}
