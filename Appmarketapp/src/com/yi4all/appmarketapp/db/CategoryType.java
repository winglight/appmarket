package com.yi4all.appmarketapp.db;

public enum CategoryType {

	APP(0, "APP"),
	GAME(1, "GAME"),
	ADULT(2, "ADULT");

	private final String displayName;
	
	private int value = 0;

	CategoryType(int value, String displayName) {
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
