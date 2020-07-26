package com.mercedes.spotfinder.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercedes.spotfinder.appconstants.AppConstants;
import com.mercedes.spotfinder.dao.ExternalEndPoints;
import com.mercedes.spotfinder.exception.BusinessException;
import com.mercedes.spotfinder.exception.ProcessingException;
import com.mercedes.spotfinder.model.Geocode;
import com.mercedes.spotfinder.service.GeocodesService;

/**
 * Service to fetch the geographical coordinates of a location name
 * @author lincoln
 *
 */
@Service
public class GeocodesServiceImpl implements GeocodesService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GeocodesServiceImpl.class);
	
	@Autowired
	private ExternalEndPoints endpoint;
	
	/**
	 * Uses Here API's find coordinates to get the coordinates of a location Name.
	 * 
	 * @param locationName => user's input 
	 * @return code => type Geocode
	 * @exception BusinessException => raised when user gives invalid input
	 * @exception JsonProcessingException => raised due to json processing 
	 */
	@Override
	public Geocode findGeocode(String locationName) throws BusinessException, ProcessingException {
		// makes call to HERE's API
		String response = endpoint.getGeoCodes(locationName);
		JsonNode coordinates = getLongitudeAndLattitude(response);
		Geocode code = new Geocode(locationName, 
				coordinates.get(AppConstants.LATITUDE).toString(), coordinates.get(AppConstants.LONGITUDE).toString());
		return code;
	}
	
	/*
	 * Parses the JSON data and fetches only the coordinates of the location
	 */
	private JsonNode getLongitudeAndLattitude(String response) throws BusinessException, ProcessingException {
		JsonNode coordinates = null;
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode jsonNode = objectMapper.readTree(response);
			JsonNode view = jsonNode.get(AppConstants.RESPONSE).get(AppConstants.VIEW);
			if (view.size() != 0) {
				coordinates = jsonNode.get(AppConstants.RESPONSE).get(AppConstants.VIEW).get(0)
						.get(AppConstants.RESULT).get(0).get(AppConstants.LOCATION).get(AppConstants.DISPLAY_POSITION);
				LOGGER.info("coordinates => {}", coordinates.toPrettyString());
			} else {
				throw new BusinessException(AppConstants.LOCATION_NAME_INVALID);
			}
		} catch (JsonProcessingException e) {
			throw new ProcessingException(AppConstants.PROCESSING_EXCEPTION);
		} 
		return coordinates;
	}
}
