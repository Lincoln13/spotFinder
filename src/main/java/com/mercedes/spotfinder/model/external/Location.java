package com.mercedes.spotfinder.model.external;

public class Location {
	private Double[] position;
	private Address address;

	public Double[] getPosition() {
		return position;
	}

	public void setPosition(Double[] position) {
		this.position = position;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}
