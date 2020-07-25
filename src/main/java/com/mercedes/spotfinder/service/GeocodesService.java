package com.mercedes.spotfinder.service;

import com.mercedes.spotfinder.exception.BusinessException;
import com.mercedes.spotfinder.exception.ProcessingException;
import com.mercedes.spotfinder.model.Geocode;

public interface GeocodesService {
	Geocode findGeocode(String locationName) throws BusinessException, ProcessingException;
}
