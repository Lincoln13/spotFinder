package com.mercedes.spotfinder.model.App;

public class AppResponse {
	
	private String location;
	private RestaurantResponse[] restaurant;
	private CommonResponse[] chargingStations;
	private String message;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
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
	
}
