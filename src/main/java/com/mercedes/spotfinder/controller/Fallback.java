package com.mercedes.spotfinder.controller;

import javax.websocket.OnError;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mercedes.spotfinder.model.App.AppResponse;

@RestController
public class Fallback {

	@OnError
	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public ResponseEntity<AppResponse> fallback(){
		AppResponse response = new AppResponse();
		response.setSystemMessage("Something went wrong! Please try again.");
		return ResponseEntity.ok(response);
	}
}
