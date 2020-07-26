package com.mercedes.spotfinder.cache.service;

import com.mercedes.spotfinder.model.App.AppResponse;
import com.mercedes.spotfinder.model.cache.CacheDataStore;

public interface CacheService {

	CacheDataStore findFromCache(String locationName);

	void storeToCache(String locationName, AppResponse appResponse);

}
