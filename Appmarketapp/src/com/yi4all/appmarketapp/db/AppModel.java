package com.yi4all.appmarketapp.db;

import java.io.Serializable;
import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "apps")
public class AppModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8767926770115497133L;

	public static final String TAG = "AppModel";
	
	public static final String DESCRIPTION = "DESCRIPTION";
	public final static String APPNAME = "APPNAME";
	public final static String SERVERID = "SERVERID";
	public final static String PRICE = "PRICE";
	public final static String DOWNURL = "DOWNURL";
	public final static String CATEGORY = "CATEGORY";
	public final static String AUTHOR = "AUTHOR";
	public final static String AUTHORID = "AUTHORID";
	public final static String APPVERSION = "APPVERSION";
	public final static String APPVERSIONCODE = "APPVERSIONCODE";
	public final static String PACKAGENAME = "PACKAGENAME";
	public final static String MINSDKVERSION = "MINSDKVERSION";
	public final static String TARGETSDKVERSION = "TARGETSDKVERSION";
	public final static String ICONURL = "ICONURL";
	public final static String DOWNLOADS = "DOWNLOADS";
	public final static String TOPPOSITION = "TOPPOSITION";
	public final static String DELETEFLAG = "DELETEFLAG";
	public final static String CREATEDAT = "CREATEDAT";

	@DatabaseField
	private long id = -1;
	@DatabaseField(columnName = SERVERID)
	private String serverId;
	@DatabaseField(columnName = APPNAME)
	private String appname;
	@DatabaseField(columnName = PRICE)
	private int price;
	@DatabaseField(columnName = DESCRIPTION)
	private String description;
	@DatabaseField(columnName = DOWNURL)
	private String downurl;
	@DatabaseField(columnName = CATEGORY)
	private CategoryModel category;
	@DatabaseField(columnName = AUTHOR)
	private String author;
	@DatabaseField(columnName = AUTHORID)
	private Long authorId;
	@DatabaseField(columnName = APPVERSION)
    private String appVersion;
	@DatabaseField(columnName = APPVERSIONCODE)
    private String appVersionCode;
	@DatabaseField(columnName = PACKAGENAME)
    private String packageName;
	@DatabaseField(columnName = MINSDKVERSION)
    private String minSdkVersion;
	@DatabaseField(columnName = TARGETSDKVERSION)
    private String targetSdkVersion;
	@DatabaseField(columnName = ICONURL)
    private String iconUrl;
	@DatabaseField(columnName = DOWNLOADS)
    private Long downloads;
	@DatabaseField(columnName = TOPPOSITION)
    private Long topPosition;//Top ten position: 1 - 10
	@DatabaseField(columnName = DOWNURL)
    private boolean deleteFlag;
	@DatabaseField(columnName = CREATEDAT)
	private Date createdAt;

	public AppModel() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAppname() {
		return appname;
	}

	public void setAppname(String appname) {
		this.appname = appname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDownurl() {
		return downurl;
	}

	public void setDownurl(String downurl) {
		this.downurl = downurl;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public String getAppVersionCode() {
		return appVersionCode;
	}

	public void setAppVersionCode(String appVersionCode) {
		this.appVersionCode = appVersionCode;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getMinSdkVersion() {
		return minSdkVersion;
	}

	public void setMinSdkVersion(String minSdkVersion) {
		this.minSdkVersion = minSdkVersion;
	}

	public String getTargetSdkVersion() {
		return targetSdkVersion;
	}

	public void setTargetSdkVersion(String targetSdkVersion) {
		this.targetSdkVersion = targetSdkVersion;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public Long getDownloads() {
		return downloads;
	}

	public void setDownloads(Long downloads) {
		this.downloads = downloads;
	}

	public Long getTopPosition() {
		return topPosition;
	}

	public void setTopPosition(Long topPosition) {
		this.topPosition = topPosition;
	}

	public boolean isDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getServerId() {
		return serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
	}

	public CategoryModel getCategory() {
		return category;
	}

	public void setCategory(CategoryModel category) {
		this.category = category;
	}


}
