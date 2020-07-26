package com.mercedes.spotfinder.testvalues;

import com.mercedes.spotfinder.model.Geocode;
import com.mercedes.spotfinder.model.external.Category;
import com.mercedes.spotfinder.model.external.ExtResponse;
import com.mercedes.spotfinder.model.external.Items;
import com.mercedes.spotfinder.model.external.Results;

public class TestValues {
	public static ExtResponse getTestResponse() {
		ExtResponse response = new ExtResponse();
		Category category = new Category();
		category.setTitle("testCategory");
		Double[] testPos = {34.0, 45.0};
		
		Items[] item = new Items[5];
		Items i0 = new Items();
		i0.setDistance("34");
		i0.setCategory(category);
		i0.setPosition(testPos);
		item[0] = i0;
		
		
		Items i1 = new Items();
		i1.setDistance("42");
		i1.setCategory(category);
		i1.setPosition(testPos);
		item[1] = i1;
		
		Items i2 = new Items();
		i2.setDistance("120");
		i2.setCategory(category);
		i2.setPosition(testPos);
		item[2] = i2;
		
		Items i3 = new Items();
		i3.setDistance("4");
		i3.setCategory(category);
		i3.setPosition(testPos);
		item[3] = i3;
		
		Items i4 = new Items();
		i4.setDistance("100");
		i4.setCategory(category);
		i4.setPosition(testPos);
		item[4] = i4;
		
		Results result = new Results();
		result.setItems(item);
		response.setResults(result);
		
		return response;
	}
	
	public static Geocode getTestGeocode() {
		return new Geocode("test", "34", "45");
	}
}
