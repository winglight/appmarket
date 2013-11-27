package models.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.OneToOne;

import models.AppModel;
import models.UserModel;
import play.data.format.Formats;

public class SimpleAppModel implements Serializable {

	public Long id;
	
    public String appname;
    
    public String description;
    
    public String downurl;
    
    public Long authorId;
    
    public String author;
    
    public String appVersion;
    
    public String appVersionCode;
    
    public String packageName;
    
    public String minSdkVersion;
    
    public String targetSdkVersion;
    
    public String iconUrl;
    
    public Long downloads;
    
    public Long topPosition;//Top ten position: 1 - 10
    
    public boolean deleteFlag;
    
	@Formats.DateTime(pattern="yyyy-MM-dd")
	public Date createdAt; 
    
    public SimpleAppModel(){
    	
    }
    
    public static SimpleAppModel transferAM(AppModel am){
    	if(am == null)    	return null;
    	
    	SimpleAppModel sam = new SimpleAppModel();
    	sam.id = am.id;
    	sam.appname = am.appname;
    	sam.description = am.description;
    	sam.downurl = am.downurl;
    	sam.authorId = am.author.id;
    	sam.author = am.author.name;
    	sam.appVersion = am.appVersion;
    	sam.appVersionCode = am.appVersionCode;
    	sam.packageName = am.packageName;
    	sam.minSdkVersion = am.minSdkVersion;
    	sam.targetSdkVersion = am.targetSdkVersion;
    	sam.iconUrl = am.iconUrl;
    	sam.downloads = am.downloads;
    	sam.topPosition = am.topPosition;
    	sam.deleteFlag = am.deleteFlag;
    	sam.createdAt = am.createdAt;
    	
    	return sam;
    }
    
    public static List<SimpleAppModel> transferAM(List<AppModel> amList){
    	if(amList == null) return null;
    	
    	ArrayList<SimpleAppModel> samList = new ArrayList<SimpleAppModel>();
    	for(AppModel am : amList){
    		samList.add(transferAM(am));
    	}
    	
    	return samList;
    }
}
