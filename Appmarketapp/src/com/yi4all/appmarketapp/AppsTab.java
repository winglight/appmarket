package com.yi4all.appmarketapp;

public enum AppsTab {

	HOTS(1, "HOTS"),
	CATEGORY(0, "CATEGORY"),
	NEWEST(2, "NEWEST"),
	ADULT(3, "ADULT"),
	UPLOAD(4, "UPLOAD");

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
