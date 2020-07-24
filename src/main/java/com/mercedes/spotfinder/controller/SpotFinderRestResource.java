package com.mercedes.spotfinder.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mercedes.spotfinder.exception.BusinessException;
import com.mercedes.spotfinder.model.Geocode;
import com.mercedes.spotfinder.service.GeoCodesService;

@RestController
@RequestMapping("/findspots")
public class SpotFinderRestResource {
	Logger logger = LoggerFactory.getLogger(SpotFinderRestResource.class);
	@Autowired
	private GeoCodesService codeService;

	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	public ResponseEntity<String> findYourSpots(@PathVariable("name") String locationName) {
		try {
			Geocode code = codeService.findGeocode(locationName);
			logger.info("location => {} long => {} latt => {}", code.getLocationName(), code.getLongitude(), code.getLattitude());
		} catch (JsonProcessingException | BusinessException e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(locationName);
	}
}
