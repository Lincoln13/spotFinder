package com.mercedes.spotfinder.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mercedes.spotfinder.exception.BusinessException;
import com.mercedes.spotfinder.model.App.AppResponse;
import com.mercedes.spotfinder.service.SpotFinderService;

@RestController
@RequestMapping("/findspots")
public class SpotFinderRestResource {
	Logger logger = LoggerFactory.getLogger(SpotFinderRestResource.class);
	@Autowired
	private SpotFinderService service;

	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	public ResponseEntity<AppResponse> findYourSpots(@PathVariable("name") String locationName) {
		AppResponse response = new AppResponse();
		try {
			response = service.findAllThings(locationName);
			response.setHttpStatus(HttpStatus.OK);
		} catch (JsonProcessingException | BusinessException e) {
			response.setLocation(locationName);
			logger.error(e.getMessage());
			response.setSystemMessage(e.getMessage());
			response.setHttpStatus(HttpStatus.BAD_REQUEST);
		} 
		return ResponseEntity.ok(response);
	}
}
