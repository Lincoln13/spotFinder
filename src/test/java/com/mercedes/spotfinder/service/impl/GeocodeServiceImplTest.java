package com.mercedes.spotfinder.service.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import com.mercedes.spotfinder.appconstants.AppConstants;
import com.mercedes.spotfinder.dao.ExternalEndPoints;
import com.mercedes.spotfinder.exception.BusinessException;
import com.mercedes.spotfinder.exception.ProcessingException;
import com.mercedes.spotfinder.model.Geocode;

public class GeocodeServiceImplTest {

	private GeocodesServiceImpl service = new GeocodesServiceImpl();
	private ExternalEndPoints endpoint = Mockito.mock(ExternalEndPoints.class);

	@Before
	public void before() {
		ReflectionTestUtils.setField(service, "endpoint", endpoint);
	}
	
	@Test
	public void findGeocodeTest_1() throws ProcessingException {
		String jsonValue = "{\"Response\":{\"MetaInfo\":{\"Timestamp\":\"2020-07-26T10:53:48.139+0000\"},\"View\":[]}}";
		String locationName = "test";
		Mockito.when(endpoint.getGeoCodes(locationName)).thenReturn(jsonValue);
		try {
			service.findGeocode(locationName);
		} catch (BusinessException e) {
			Assert.assertTrue(AppConstants.LOCATION_NAME_INVALID.equals(e.getMessage()));
		}
	}
	
	@Test
	public void findGeocodeTest_2() throws ProcessingException {
		String jsonValue = "{\"Response\":{\"MetaInfo\":{\"Timestamp\":\"2020-07-25T14:22:04.710+0000\"},\"View\":[{\"_type\":\"SearchResultsViewType\",\"ViewId\":0,\"Result\":[{\"Relevance\":1.0,\"MatchLevel\":\"city\",\"MatchQuality\":{\"City\":1.0},\"Location\":{\"LocationId\":\"NT_AP3PoLHpeA15ngMJxal4hC\",\"LocationType\":\"area\",\"DisplayPosition\":{\"Latitude\":51.50643,\"Longitude\":-0.12721},\"NavigationPosition\":[{\"Latitude\":51.50643,\"Longitude\":-0.12721}],\"MapView\":{\"TopLeft\":{\"Latitude\":51.68629,\"Longitude\":-0.56316},\"BottomRight\":{\"Latitude\":51.28043,\"Longitude\":0.28206}},\"Address\":{\"Label\":\"London, England, United Kingdom\",\"Country\":\"GBR\",\"State\":\"England\",\"County\":\"London\",\"City\":\"London\",\"PostalCode\":\"SW1A 2\",\"AdditionalData\":[{\"value\":\"United Kingdom\",\"key\":\"CountryName\"},{\"value\":\"England\",\"key\":\"StateName\"},{\"value\":\"London\",\"key\":\"CountyName\"}]}}}]}]}}";
		String locationName = "London";
		Mockito.when(endpoint.getGeoCodes(locationName)).thenReturn(jsonValue);
		Geocode code = null;
		try {
			code = service.findGeocode(locationName);
		} catch (BusinessException e) {
			Assert.assertTrue(AppConstants.LOCATION_NAME_INVALID.equals(e.getMessage()));
		}
		Assert.assertTrue(code.getLocationName().equals(locationName));
		Assert.assertTrue(code.getLattitude().equals("51.50643"));
		Assert.assertTrue(code.getLongitude().equals("-0.12721"));
	}
}
