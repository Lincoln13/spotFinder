package com.mercedes.spotfinder.model.external;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExtResponse {
	
	@JsonProperty
	private Results results;
	@JsonProperty
	private Search search;
	

	public Search getSearch() {
		return search;
	}

	public void setSearch(Search search) {
		this.search = search;
	}

	public Results getResults() {
		return results;
	}

	public void setResults(Results results) {
		this.results = results;
	}
	
	 public ExtResponse(){}
}
