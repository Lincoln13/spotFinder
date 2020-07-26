package com.mercedes.spotfinder.cache;

import java.util.concurrent.ConcurrentHashMap;

import com.mercedes.spotfinder.model.cache.CacheDataStore;

public class Cache {

	public static ConcurrentHashMap<String, CacheDataStore> cache = new ConcurrentHashMap<>();

	public static ConcurrentHashMap<String, CacheDataStore> getCache() {
		return cache;
	}

	public static void setCache(ConcurrentHashMap<String, CacheDataStore> cache) {
		Cache.cache = cache;
	}
	
	
}
