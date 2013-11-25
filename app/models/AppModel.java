package models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import play.data.format.Formats;
import play.data.validation.Constraints;
import play.db.ebean.Model;
import util.Constants;

import models.status.UserRole;
import models.status.UserStatus;

/**
 * It defines a APP.
 * 
 * @author yanxin
 * @since 1.0
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue(value = "apps")
public class AppModel extends Model implements Serializable{ 


	/**
	 * 
	 */
	private static final long serialVersionUID = 8954495534164720516L;

	@Id
	public Long id;
	
    public String appname;
    
    public String desc;
    
    public String downurl;
    
    @OneToOne
    public UserModel author;
    
    public String appVersion;
    
    public String appVersionCode;
    
    public String packageName;
    
    public String minSdkVersion;
    
    public String targetSdkVersion;
    
    public String iconUrl;
    
    public Long downloads;
    
	@Formats.DateTime(pattern="yyyy-MM-dd")
	public Date createdAt; 
    
    public AppModel(){
    	
    }
	
    // -- Queries
    
    public static Model.Finder<Long,AppModel> find = new Model.Finder(Long.class, AppModel.class);
    
    /**
     * Retrieve all users.
     */
    public static List<AppModel> findAll() {
        return find.all();
    }
    
    public static List<AppModel> findAppsByAuthor(UserModel author) {
        return find.where()
                .eq("author", author).findList();
    }
    
    public static List<AppModel> findAppsByHots(int page) {
        return find.where().orderBy("downloads desc, createdAt desc")
				.findPagingList(Constants.AMOUNT_PER_PAGE).getPage(page - 1)
				.getList();
    }
    
    public static AppModel findByPkg(String pkgName) {
        return find.where()
                .eq("packageName", pkgName)
                .findUnique();
    }

	@Override
	public String toString() {
		return "APP [name:" + appname
				+ "]";
	}

	public static boolean isOwner(Long app, String username) {
		return find.where()
	            .eq("author.name", username)
	            .eq("id", app)
	            .findRowCount() > 0;
	}
}
