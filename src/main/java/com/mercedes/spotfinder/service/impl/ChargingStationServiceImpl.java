package com.mercedes.spotfinder.service.impl;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercedes.spotfinder.dao.ExternalEndPoints;
import com.mercedes.spotfinder.helper.SortByDistance;
import com.mercedes.spotfinder.model.Geocode;
import com.mercedes.spotfinder.model.App.CommonResponse;
import com.mercedes.spotfinder.model.external.Items;
import com.mercedes.spotfinder.model.external.ExtResponse;
import com.mercedes.spotfinder.service.ChargingStationService;

@Service
public class ChargingStationServiceImpl extends Mapper implements ChargingStationService {

	Logger logger = LoggerFactory.getLogger(ChargingStationServiceImpl.class);
	
	@Autowired
	private ExternalEndPoints dao;
	
	@Override
	public CommonResponse[] findChargingStationsNearMe(Geocode codes) throws InterruptedException, ExecutionException {
		
		ExtResponse response = dao.getChargingStations(codes).get();
		logger.info("Found {} charging stations in {}", response.getResults().getItems().length, codes.getLocationName());
		
		Items[] items = response.getResults().getItems();
		Arrays.sort(items, new SortByDistance()); 
		
		CommonResponse[] chargingStations = new CommonResponse[3];
		for (int i = 0; i < 3; i ++ ) {
			chargingStations[i] = mappingToAppResponse(items[i]);
		}
		return chargingStations;
	}
}
