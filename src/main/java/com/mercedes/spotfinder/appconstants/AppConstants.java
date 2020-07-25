package com.mercedes.spotfinder.appconstants;

public class AppConstants {
	
	// ------------- APPLICATION CREDENTIALS --------------------------------------- //
	public static final String API_KEY = "mMzcnFutNmsMu-QHKOJmEpRHFFMl7sUzi7pxEn43-E4";
	public static final String APP_ID = "nmkVYgf1giMuKXT6MiPS";
	
	// --------------- EXTERNAL ENDPOINTS ------------------------------------------ //
	public static final String ENDPOINT_GEOCODE = "https://geocoder.ls.hereapi.com/6.2/geocode.json?searchtext={0}&gen=9&apiKey={1}";
	public static final String ENDPOINT_RESTAURANT = "https://places.ls.hereapi.com/places/v1/discover/explore?at={0},{1}&cat=restaurant&apiKey={2}";
	public static final String ENDPOINT_STATIONS = "https://places.ls.hereapi.com/places/v1/discover/search?at={0},{1}&q=charging&apiKey={2}";
	public static final String ENDPOINT_PARKING = "https://places.ls.hereapi.com/places/v1/discover/search?at={0},{1}&q=parking&apiKey={2}";
	
	// --------------- EXCEPTION MESSAGES ------------------------------------------ //
	public static final String LOCATION_NAME_INVALID = "Invalid location name!";
	public static final String PROCESSING_EXCEPTION = "It's not you! It's me!!";
	
	// --------------- JSON FIELDS ----------------------------------------------//
	public static final String LATITUDE = "Latitude";
	public static final String LONGITUDE = "Longitude";
	public static final String RESPONSE = "Response";
	public static final String VIEW = "View";
	public static final String RESULT = "Result";
	public static final String LOCATION = "Location";
	public static final String DISPLAY_POSITION = "DisplayPosition";
	
}
