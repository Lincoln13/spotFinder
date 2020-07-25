package com.mercedes.spotfinder.model.external;

public class OpeningHours {
	private String text;
	private String label;
	private String isOpen;
	private Structured[] structured;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(String isOpen) {
		this.isOpen = isOpen;
	}

	public Structured[] getStructured() {
		return structured;
	}

	public void setStructured(Structured[] structured) {
		this.structured = structured;
	}
}
