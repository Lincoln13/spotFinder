package com.mercedes.spotfinder.model.external;

public class Search {
	private Context context;
	private String ranking; // added
	
	public String getRanking() {
		return ranking;
	}

	public void setRanking(String ranking) {
		this.ranking = ranking;
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

}
