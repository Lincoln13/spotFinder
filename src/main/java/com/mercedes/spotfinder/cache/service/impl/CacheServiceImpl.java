package com.mercedes.spotfinder.cache.service.impl;

import org.joda.time.DateTime;
import org.joda.time.Minutes;
import org.springframework.stereotype.Service;

import com.mercedes.spotfinder.cache.Cache;
import com.mercedes.spotfinder.cache.service.CacheService;
import com.mercedes.spotfinder.model.App.AppResponse;
import com.mercedes.spotfinder.model.cache.CacheDataStore;

/**
 * Implementation for caching mechanism 
 *
 */
@Service
public class CacheServiceImpl implements CacheService {
	
	private static final int THRESHOLD = 31;

	@Override
	public CacheDataStore findFromCache(String locationName) {
		if (Cache.cache.containsKey(locationName)) {
			// checks if the cache data is stale
			// also checks the time difference from first created and new request. If its more than
			// the threshold value, then it hits to the endpoint.
			if (!Cache.cache.get(locationName).isStale() && 
					Minutes.minutesBetween(
							Cache.cache.get(locationName).getSetTime(), DateTime.now()).getMinutes() < THRESHOLD
					) 
			{
				return Cache.cache.get(locationName);
			}
			Cache.cache.get(locationName).setStale(true);
		}
		return null;
	}

	/**
	 * updates or creates a new entry in the cache if data is stale or missing 
	 * respectively.
	 */
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
