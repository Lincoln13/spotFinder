package com.mercedes.spotfinder.helper;

import java.util.Comparator;

import com.mercedes.spotfinder.model.restaurant.Items;

public class SortByDistance implements Comparator<Items>  {
	
	public int compare(Items item1, Items item2) 
    { 
        return Integer.parseInt(item1.getDistance()) - Integer.parseInt(item2.getDistance()); 
    } 
	
}
