package com.mercedes.spotfinder.service.impl;

import java.util.Arrays;

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
public class ChargingStationServiceImpl implements ChargingStationService {

	Logger logger = LoggerFactory.getLogger(ChargingStationServiceImpl.class);
	
	@Autowired
	private ExternalEndPoints dao;
	
	@Override
	public CommonResponse[] findChargingStationsNearMe(Geocode codes) {
		ExtResponse response = dao.getChargingStations(codes);
		logger.info("Found {} charging stations in {}", response.getResults().getItems().length, codes.getLocationName());
		
		Items[] items = response.getResults().getItems();
		
		Arrays.sort(items, new SortByDistance()); 
		
		CommonResponse[] chargingStations = new CommonResponse[3];
		for (int i = 0; i < 3; i ++ ) {
			chargingStations[i] = mapping(items[i]);
		}
		return chargingStations;
	}
	
	private CommonResponse mapping(Items item) {
		CommonResponse res = new CommonResponse();
		res.setName(item.getTitle());
		res.setCategory(item.getCategory().getTitle());
		res.setAddress(item.getVicinity());
		res.setDistance(item.getDistance());
		res.setMoreInfoRef(item.getHref());
		res.setPosition(item.getPosition());
		res.setTags(item.getTags());
		res.setOpeningHours(item.getOpeningHours());
		res.setAlternativeNames(item.getAlternativeNames());
		logger.info("{}",res.toString());
		return res;
	}
}