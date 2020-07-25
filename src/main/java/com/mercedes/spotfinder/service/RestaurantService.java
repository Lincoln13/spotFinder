package com.mercedes.spotfinder.service;

import com.mercedes.spotfinder.model.Geocode;
import com.mercedes.spotfinder.model.App.RestaurantResponse;

public interface RestaurantService {

	RestaurantResponse[] findRestaurantNearMe(Geocode codes);

}
