package com.mercedes.spotfinder.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mercedes.spotfinder.exception.BusinessException;
import com.mercedes.spotfinder.model.App.AppResponse;

public interface SpotFinderService {

	AppResponse findAllThings(String locationName) throws JsonProcessingException, BusinessException;

}
