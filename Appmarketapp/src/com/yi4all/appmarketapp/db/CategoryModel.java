package com.yi4all.appmarketapp.db;

import java.io.Serializable;
import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "category")
public class CategoryModel implements Serializable {
	/**
	 * 
	 */
	public static final long serialVersionUID = 4901273812769097177L;

	public final static String LOGTAG = "CategoryModel";
	
	public  static final String NAME = "NAME";
	public  static final String COVER = "COVER";
	public static final String FIELD_SERVERID = "SERVERID";
	public  static final String CREATED_AT = "CREATED_AT";
	public  static final String SUBSCRIBED = "SUBSCRIBED";
	
	@DatabaseField(generatedId = true)
	private long id = -1;
	@DatabaseField(columnName = FIELD_SERVERID)
	private Long serverId;// ID of server
	@DatabaseField(index = true, columnName = NAME)
	private String name;
	@DatabaseField(columnName = COVER)
	private String cover;//image path
	@DatabaseField(columnName = CREATED_AT)
	private Date createdAt; 
	@DatabaseField(columnName = SUBSCRIBED)
	private boolean subscribed=true; 
	
	public CategoryModel(){
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}


	public Long getServerId() {
		return serverId;
	}

	public void setServerId(Long serverId) {
		this.serverId = serverId;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public boolean isSubscribed() {
		return subscribed;
	}

	public void setSubscribed(boolean subscribed) {
		this.subscribed = subscribed;
	}
	
	
}
