package com.mercedes.spotfinder.service;

import com.mercedes.spotfinder.exception.BusinessException;
import com.mercedes.spotfinder.exception.ProcessingException;
import com.mercedes.spotfinder.model.App.AppResponse;

public interface SpotFinderService {

	AppResponse findAllThings(String locationName) throws ProcessingException, BusinessException;

}
