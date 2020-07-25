package com.mercedes.spotfinder.service.impl;

import com.mercedes.spotfinder.model.App.CommonResponse;
import com.mercedes.spotfinder.model.external.Items;

public abstract class Mapper {

	public CommonResponse mappingToAppResponse(Items item) {
		CommonResponse res = new CommonResponse();

		res.setName(item.getTitle());
		res.setCategory(item.getCategory().getTitle());
		res.setAddress(item.getVicinity());
		res.setDistance(item.getDistance());
		res.setMoreInfoRef(item.getHref());
		res.setPosition(item.getPosition());
		res.setTags(item.getTags());
		res.setOpeningHours(item.getOpeningHours());
		res.setAlternativeNames(item.getAlternativeNames());

		return res;
	}
}
