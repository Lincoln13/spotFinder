package com.mercedes.spotfinder.service;

import java.util.concurrent.ExecutionException;

import com.mercedes.spotfinder.model.Geocode;
import com.mercedes.spotfinder.model.App.CommonResponse;

public interface ChargingStationService {

	CommonResponse[] findChargingStationsNearMe(Geocode codes) throws InterruptedException, ExecutionException;

}
