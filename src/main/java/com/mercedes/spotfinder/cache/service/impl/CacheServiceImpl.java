package com.mercedes.spotfinder.cache.service.impl;

import org.joda.time.DateTime;
import org.joda.time.Minutes;
import org.springframework.stereotype.Service;

import com.mercedes.spotfinder.cache.Cache;
import com.mercedes.spotfinder.cache.service.CacheService;
import com.mercedes.spotfinder.model.App.AppResponse;
import com.mercedes.spotfinder.model.cache.CacheDataStore;

@Service
public class CacheServiceImpl implements CacheService {
	
	@Override
	public CacheDataStore findFromCache(String locationName) {
		if (Cache.cache.containsKey(locationName)) {
			if (!Cache.cache.get(locationName).isStale() && Minutes.minutesBetween(Cache.cache.get(locationName).getSetTime(), DateTime.now()).getMinutes() < 31) {
				return Cache.cache.get(locationName);
			}
			Cache.cache.get(locationName).setStale(true);
		}
		return null;
	}

	@Override
	public void storeToCache(String locationName, AppResponse appResponse) {
		CacheDataStore cacheModel = new CacheDataStore();
		cacheModel.setRestaurant(appResponse.getRestaurant());
		cacheModel.setChargingStations(appResponse.getChargingStations());
		cacheModel.setSetTime(DateTime.now());
		cacheModel.setStale(false);
		
		Cache.cache.put(locationName, cacheModel);
		
	}
}
