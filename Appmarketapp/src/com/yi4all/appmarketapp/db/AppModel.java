package com.yi4all.appmarketapp.db;

import java.io.Serializable;
import java.util.Date;

import models.UserModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@JsonIgnoreProperties(ignoreUnknown = true)
@DatabaseTable(tableName = "apps")
public class AppModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8767926770115497133L;

	public static final String TAG = "AppModel";
	
	public static final String DESCRIPTION = "DESCRIPTION";
	public final static String APPNAME = "APPNAME";
	public final static String DOWNURL = "DOWNURL";
	public final static String AUTHOR = "AUTHOR";
	public final static String AUTHORID = "AUTHORID";
	public final static String ENDDATE = "ENDDATE";
	public final static String LASTUPDATETIME = "LASTUPDATETIME";
	public final static String HP = "HP";

	@DatabaseField(generatedId = true)
	private long id = -1;
	@DatabaseField(columnName = APPNAME)
	private String appname;
	@DatabaseField(columnName = DESCRIPTION)
	private String description;
	@DatabaseField(columnName = DOWNURL)
	private String downurl;
	@DatabaseField(columnName = AUTHOR)
	public String author;
	@DatabaseField(columnName = AUTHORID)
	public Long authorId;
	@DatabaseField(columnName = DOWNURL)
    public String appVersion;
	@DatabaseField(columnName = DOWNURL)
    public String appVersionCode;
	@DatabaseField(columnName = DOWNURL)
    public String packageName;
	@DatabaseField(columnName = DOWNURL)
    public String minSdkVersion;
	@DatabaseField(columnName = DOWNURL)
    public String targetSdkVersion;
	@DatabaseField(columnName = DOWNURL)
    public String iconUrl;
	@DatabaseField(columnName = DOWNURL)
    public Long downloads;
	@DatabaseField(columnName = DOWNURL)
    public Long topPosition;//Top ten position: 1 - 10
	@DatabaseField(columnName = DOWNURL)
    public boolean deleteFlag;
	@DatabaseField(columnName = LASTUPDATETIME)
	private Date createdAt;

	public AppModel() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


}
