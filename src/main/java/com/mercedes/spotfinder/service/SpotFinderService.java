package com.mercedes.spotfinder.service;

import com.mercedes.spotfinder.model.App.AppResponse;

public interface SpotFinderService {

	AppResponse findAllThings(String locationName);

}
