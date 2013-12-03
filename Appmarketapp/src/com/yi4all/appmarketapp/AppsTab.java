package com.yi4all.appmarketapp;

public enum AppsTab {

	APP(0, "APP"),
	GAME(1, "GAME"),
	HOTS(2, "HOTS"),
	NEWEST(3, "NEWEST"),
	ADULT(4, "ADULT"),
	UPLOAD(5, "UPLOAD");

	private int value = 0;
	
	private final String displayName;

	AppsTab(int value, String displayName) {
		this.value = value;
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}
	
	public int value() {
        return this.value;
    }
	
	
}
