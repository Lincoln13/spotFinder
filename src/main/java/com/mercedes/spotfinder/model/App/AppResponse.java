package com.mercedes.spotfinder.model.App;

public class AppResponse {
	
	private String location;
	private AppRestaurantResponse[] restaurant;
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public AppRestaurantResponse[] getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(AppRestaurantResponse[] restaurant) {
		this.restaurant = restaurant;
	}
	
}
