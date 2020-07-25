package com.mercedes.spotfinder.model.App;

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
