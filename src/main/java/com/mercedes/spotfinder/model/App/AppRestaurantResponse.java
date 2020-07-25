package com.mercedes.spotfinder.model.App;

import java.util.Arrays;

import com.mercedes.spotfinder.model.restaurant.Tags;

public class AppRestaurantResponse {
	
	private String name;
	private String distance;
	private String address;
	private String category;
	private String moreInfoRef;
	private Tags[] tags;
	private Double[] position;
	
	// TODO: need to place parking spots near here!
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getMoreInfoRef() {
		return moreInfoRef;
	}
	public void setMoreInfoRef(String moreInfoRef) {
		this.moreInfoRef = moreInfoRef;
	}
	public Tags[] getTags() {
		return tags;
	}
	public void setTags(Tags[] tags) {
		this.tags = tags;
	}
	public Double[] getPosition() {
		return position;
	}
	public void setPosition(Double[] position) {
		this.position = position;
	}
	
	@Override
	public String toString() {
		return "AppRestaurantResponse [name=" + name + ", distance=" + distance + ", address=" + address + ", category="
				+ category + ", moreInfoRef=" + moreInfoRef + ", tags=" + Arrays.toString(tags) + ", position="
				+ Arrays.toString(position) + "]";
	}
}
