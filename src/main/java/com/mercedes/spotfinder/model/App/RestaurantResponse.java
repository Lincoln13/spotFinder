package com.mercedes.spotfinder.model.App;

/**
 * Restaurant Response will hold parking details
 * as the assumption is user would like to look for
 * the parking spots near the restaurant/pub
 *
 */
public class RestaurantResponse {
	
	private CommonResponse restaurantResponse;
	private CommonResponse[] parkingSpotResponse;

	public CommonResponse[] getParkingSpotResponse() {
		return parkingSpotResponse;
	}

	public void setParkingSpotResponse(CommonResponse[] parkingSpotResponse) {
		this.parkingSpotResponse = parkingSpotResponse;
	}

	public CommonResponse getRestaurantResponse() {
		return restaurantResponse;
	}

	public void setRestaurantResponse(CommonResponse restaurantResponse) {
		this.restaurantResponse = restaurantResponse;
	}
}
