package com.mercedes.spotfinder.model;

public class Geocode {
	private String locationName;
	private String longitude;
	private String latitude;
	
	public Geocode() {}
	
	public Geocode(String locationName, String latitude, String longitude) {
		this.locationName = locationName;
		this.longitude = longitude;  
		this.latitude = latitude;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLattitude() {
		return latitude;
	}
	public void setLattitude(String latitude) {
		this.latitude = latitude;
	}
	
	@Override
	public String toString() {
		return "location Name => " + this.locationName + 
				" longitude => " + this.longitude + 
				" lattitude => " + this.latitude;
	}
}
