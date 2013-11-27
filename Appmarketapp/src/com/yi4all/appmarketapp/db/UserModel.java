package com.yi4all.appmarketapp.db;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@JsonIgnoreProperties(ignoreUnknown = true)
@DatabaseTable(tableName = "user")
public class UserModel implements Serializable {

	public static final String TAG = "UserModel";
	
	public static final String FIELD_EMAIL = "EMAIL";
	public final static String FIELD_PASSWORD = "PASSWORD";
	public final static String FIELD_SID = "DEVICE_ID";
	public final static String FIELD_VIP = "VIP";
	public final static String FIELD_ENDDATE = "ENDDATE";
	public final static String FIELD_LASTUPDATETIME = "LASTUPDATETIME";
	public final static String FIELD_HP = "HP";

	@DatabaseField(generatedId = true)
	private long id = -1;
	@DatabaseField(columnName = FIELD_EMAIL)
	private String email;
	@DatabaseField(columnName = FIELD_PASSWORD)
	private String password;
	@DatabaseField(columnName = FIELD_SID)
	private String deviceId;
	@DatabaseField(columnName = FIELD_LASTUPDATETIME)
	private Date lastUpdateTime;
	@DatabaseField(columnName = FIELD_HP)
	private int hp=5;
	
    private Date expirationDate;
	
	private String tokenid;
    
	private Long tokenExpirationTime;

	public UserModel() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String sid) {
		this.deviceId = sid;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getTokenid() {
		return tokenid;
	}

	public void setTokenid(String tokenid) {
		this.tokenid = tokenid;
	}

	public Long getTokenExpirationTime() {
		return tokenExpirationTime;
	}

	public void setTokenExpirationTime(Long tokenExpirationTime) {
		this.tokenExpirationTime = tokenExpirationTime;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

}
