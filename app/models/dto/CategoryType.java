package models.dto;

public enum CategoryType {

	APP("APP"),
	GAME("GAME"),
	ADULT("ADULT");

	private final String displayName;

	CategoryType(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}
}
