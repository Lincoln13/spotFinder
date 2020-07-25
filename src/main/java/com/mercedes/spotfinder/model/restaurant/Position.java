package com.mercedes.spotfinder.model.restaurant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Position {
	private Double lattitude;
//	private String longitude;
	
	@JsonCreator
	public Position (@JsonProperty("position") Double position ) {
	    this.lattitude = position;
	}
	
	public Double getLattitude() {
		return lattitude;
	}
	public void setLattitude(Double lattitude) {
		this.lattitude = lattitude;
	}
//	public String getLongitude() {
//		return longitude;
//	}
//	public void setLongitude(String longitude) {
//		this.longitude = longitude;
//	}
	
}
