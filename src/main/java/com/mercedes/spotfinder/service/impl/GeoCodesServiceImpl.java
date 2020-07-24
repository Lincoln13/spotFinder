package com.mercedes.spotfinder.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercedes.spotfinder.dao.ExternalEndPoints;
import com.mercedes.spotfinder.exception.BusinessException;
import com.mercedes.spotfinder.model.Geocode;
import com.mercedes.spotfinder.service.GeoCodesService;

@Service
public class GeoCodesServiceImpl implements GeoCodesService {
	
	Logger logger = LoggerFactory.getLogger(GeoCodesServiceImpl.class);
	
	@Autowired
	private ExternalEndPoints endpoint;
	
	@Override
	public Geocode findGeocode(String locationName) throws BusinessException, JsonProcessingException {
		String response = endpoint.findGeoCodes(locationName);
		JsonNode coordinates = getLongitudeAndLattitude(response);
		Geocode code = new Geocode(locationName, 
				coordinates.get("Latitude").toString(), coordinates.get("Longitude").toString());
		return code;
	}
	
	private JsonNode getLongitudeAndLattitude(String response) throws BusinessException, JsonProcessingException {
		JsonNode coordinates = null;
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode jsonNode = objectMapper.readTree(response);
			JsonNode view = jsonNode.get("Response").get("View");
			if (view.size() != 0) {
				coordinates = jsonNode.get("Response").get("View").get(0).get("Result").get(0).get("Location").get("DisplayPosition");
				logger.info("coordinates => {}", coordinates.toPrettyString());
			} else {
				throw new BusinessException("Invalid location name!");
			}
		} catch (JsonProcessingException e) {
			throw e;
		} 
		return coordinates;
	}
}
