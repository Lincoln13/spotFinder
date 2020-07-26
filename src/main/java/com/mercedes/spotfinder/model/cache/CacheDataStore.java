package com.mercedes.spotfinder.model.cache;

import org.joda.time.DateTime;

import com.mercedes.spotfinder.model.App.CommonResponse;
import com.mercedes.spotfinder.model.App.RestaurantResponse;

public class CacheDataStore {

	private RestaurantResponse[] restaurant;
	private CommonResponse[] chargingStations;
	private DateTime setTime;
	private boolean isStale;
	
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
	public DateTime getSetTime() {
		return setTime;
	}
	public void setSetTime(DateTime setTime) {
		this.setTime = setTime;
	}
	public boolean isStale() {
		return isStale;
	}
	public void setStale(boolean isStale) {
		this.isStale = isStale;
	}
	
}
