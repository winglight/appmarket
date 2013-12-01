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
	public  static final String TYPE = "TYPE";
	public  static final String CREATED_AT = "CREATED_AT";
	
	@DatabaseField
	private long id = -1;
	@DatabaseField(index = true, columnName = NAME)
	private String name;
	@DatabaseField(columnName = TYPE)
	private CategoryType type;
	@DatabaseField(columnName = CREATED_AT)
	private Date createdAt; 
	
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

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public CategoryType getType() {
		return type;
	}

	public void setType(CategoryType type) {
		this.type = type;
	}

	
	
}
