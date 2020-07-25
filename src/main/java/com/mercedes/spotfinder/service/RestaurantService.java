package com.mercedes.spotfinder.service;

import com.mercedes.spotfinder.model.Geocode;
import com.mercedes.spotfinder.model.App.AppRestaurantResponse;

public interface RestaurantService {

	AppRestaurantResponse[] findRestaurant(Geocode codes);

}
