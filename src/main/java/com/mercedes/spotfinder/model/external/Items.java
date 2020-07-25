package com.mercedes.spotfinder.model.external;

public class Items {
	private Double[] position;
	private String distance;
	private String title;
	private String averageRating;
	private Category category;
	private String icon;
	private String vicinity;
	private Having[] having;
	private String type;
	private String href;
	private Tags[] tags;
	private String id;
	private OpeningHours openingHours;
	private String[] chainIds; // new
	private AlternativeNames[] alternativeNames;

	public Double[] getPosition() {
		return position;
	}

	public void setPosition(Double[] position) {
		this.position = position;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(String averageRating) {
		this.averageRating = averageRating;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getVicinity() {
		return vicinity;
	}

	public void setVicinity(String vicinity) {
		this.vicinity = vicinity;
	}

	public Having[] getHaving() {
		return having;
	}

	public void setHaving(Having[] having) {
		this.having = having;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public Tags[] getTags() {
		return tags;
	}

	public void setTags(Tags[] tags) {
		this.tags = tags;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public OpeningHours getOpeningHours() {
		return openingHours;
	}

	public void setOpeningHours(OpeningHours openingHours) {
		this.openingHours = openingHours;
	}

	public AlternativeNames[] getAlternativeNames() {
		return alternativeNames;
	}

	public void setAlternativeNames(AlternativeNames[] alternativeNames) {
		this.alternativeNames = alternativeNames;
	}

	public String[] getChainIds() {
		return chainIds;
	}

	public void setChainIds(String[] chainIds) {
		this.chainIds = chainIds;
	}

}
