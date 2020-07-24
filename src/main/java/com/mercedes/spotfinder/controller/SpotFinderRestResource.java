package com.mercedes.spotfinder.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/findspots")
public class SpotFinderRestResource {

	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	public ResponseEntity<String> findYourSpots(@PathVariable("name") String locationName) {
		
		return ResponseEntity.ok(locationName);
	}
}
