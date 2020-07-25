package com.mercedes.spotfinder.controller;

import javax.websocket.OnError;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mercedes.spotfinder.dao.ExternalEndPoints;
import com.mercedes.spotfinder.model.App.AppResponse;
import com.mercedes.spotfinder.service.SpotFinderService;

@RestController
@RequestMapping("/findspots")
public class SpotFinderRestResource {
	Logger logger = LoggerFactory.getLogger(SpotFinderRestResource.class);
	@Autowired
	private SpotFinderService service;
	
	@Autowired
	private ExternalEndPoints ex;

	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	public ResponseEntity<AppResponse> findYourSpots(@PathVariable("name") String locationName) {
		AppResponse response = new AppResponse();
		response = service.findAllThings(locationName);
//		ex.findResturants_2();
//		ex.findStations();
		return ResponseEntity.ok(response);
	}
	
	@OnError
	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public ResponseEntity<String> fallback(){
		String msg = "lol! your code just failed";
		return ResponseEntity.ok(msg);
	}
}
