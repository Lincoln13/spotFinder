package com.mercedes.spotfinder.model.App;

import org.springframework.http.HttpStatus;

/**
 * Model class to be used as the response entity 
 * for the application - spotfinder
 *
 */
public class AppResponse {
	
	private String location;
	private RestaurantResponse[] restaurant;
	private CommonResponse[] chargingStations;
	private HttpStatus httpStatus;
	private String systemMessage;
	
	public HttpStatus getMessage() {
		return httpStatus;
	}
	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public RestaurantResponse[] getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(RestaurantResponse[] restaurant) {
		this.restaurant = restaurant;
	}
	public CommonResponse[] getChargingStations() {
		return chargingStations;
	}
	public void setChargingStations(CommonResponse[] chargingStations) {
		this.chargingStations = chargingStations;
	}
	public String getSystemMessage() {
		return systemMessage;
	}
	public void setSystemMessage(String systemMessage) {
		this.systemMessage = systemMessage;
	}
	
}
