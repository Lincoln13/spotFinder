package com.mercedes.spotfinder.helper;

import java.util.Comparator;

import com.mercedes.spotfinder.model.external.Items;

public class SortByDistance implements Comparator<Items>  {
	
	// class to compare and sort by distance.
	public int compare(Items item1, Items item2) 
    { 
        return Integer.parseInt(item1.getDistance()) - Integer.parseInt(item2.getDistance()); 
    } 
	
}
