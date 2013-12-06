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

import models.dto.CategoryType;
import models.dto.PageInfo;
import models.dto.SimpleAppModel;
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
public class AppModel extends Model implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8954495534164720516L;

	@Id
	public Long id;

	public String appname;

	public int price=0;

	public String description;

	public String downurl;

	@OneToOne
	public UserModel author;

	@OneToOne
	public CategoryModel category;

	public String appVersion;

	public String appVersionCode;

	public String packageName;

	public String minSdkVersion;

	public String targetSdkVersion;

	public String iconUrl;

	public Long downloads=0L;

	public Long topPosition;// Top ten position: 1 - 10

	public boolean deleteFlag;

	@Formats.DateTime(pattern = "yyyy-MM-dd")
	public Date createdAt;

	public AppModel() {

	}

	// -- Queries

	public static Model.Finder<Long, AppModel> find = new Model.Finder(
			Long.class, AppModel.class);

	/**
	 * Retrieve all users.
	 */
	public static List<AppModel> findAll() {
		return find.all();
	}

	public static List<AppModel> findAppsByAuthor(UserModel author) {
		return find.where().eq("deleteFlag", false).eq("author", author)
				.findList();
	}

	public static List<SimpleAppModel> findAppsByHots(int page,
			Date lastUpdateDate) {
		if (lastUpdateDate != null) {
			List<AppModel> list = find.where().eq("deleteFlag", false)
					.gt("createdAt", lastUpdateDate)
					.orderBy("downloads,createdAt desc")
					.findPagingList(Constants.AMOUNT_PER_PAGE)
					.getPage(page - 1).getList();
			return SimpleAppModel.transferAM(list);
		} else {
			List<AppModel> list = find.where().eq("deleteFlag", false)
					.orderBy("downloads,createdAt desc")
					.findPagingList(Constants.AMOUNT_PER_PAGE)
					.getPage(page - 1).getList();
			return SimpleAppModel.transferAM(list);
		}
	}

	public static List<SimpleAppModel> findAppsByCategory(Long category,
			int page, Date lastUpdateDate) {
		if (lastUpdateDate != null) {
			List<AppModel> list = find.where().eq("deleteFlag", false)
					.eq("category.id", category)
					.gt("createdAt", lastUpdateDate)
					.orderBy("downloads,createdAt desc")
					.findPagingList(Constants.AMOUNT_PER_PAGE)
					.getPage(page - 1).getList();
			return SimpleAppModel.transferAM(list);
		} else {
			List<AppModel> list = find.where().eq("deleteFlag", false)
					.eq("category.id", category)
					.orderBy("downloads,createdAt desc")
					.findPagingList(Constants.AMOUNT_PER_PAGE)
					.getPage(page - 1).getList();
			return SimpleAppModel.transferAM(list);
		}
	}
	
	public static List<SimpleAppModel> findAppsByCategoryType(Long categoryType,
			int page, Date lastUpdateDate) {
		if (lastUpdateDate != null) {
			List<AppModel> list = find.where().eq("deleteFlag", false)
					.eq("category.type", categoryType.intValue())
					.gt("createdAt", lastUpdateDate)
					.orderBy("downloads,createdAt desc")
					.findPagingList(Constants.AMOUNT_PER_PAGE)
					.getPage(page - 1).getList();
			return SimpleAppModel.transferAM(list);
		} else {
			List<AppModel> list = find.where().eq("deleteFlag", false)
					.eq("category.type", categoryType.intValue())
					.orderBy("downloads,createdAt desc")
					.findPagingList(Constants.AMOUNT_PER_PAGE)
					.getPage(page - 1).getList();
			return SimpleAppModel.transferAM(list);
		}
	}

	public static List<SimpleAppModel> findAppsByNewest(int page,
			Date lastUpdateDate) {
		if (lastUpdateDate != null) {
			List<AppModel> list = find.where().eq("deleteFlag", false)
					.gt("createdAt", lastUpdateDate)
					.orderBy("createdAt desc")
					.findPagingList(Constants.AMOUNT_PER_PAGE)
					.getPage(page - 1).getList();
			return SimpleAppModel.transferAM(list);
		} else {
			List<AppModel> list = find.where().eq("deleteFlag", false)
					.orderBy("createdAt desc")
					.findPagingList(Constants.AMOUNT_PER_PAGE)
					.getPage(page - 1).getList();
			return SimpleAppModel.transferAM(list);
		}
	}

	public static PageInfo getHotAppsPageInfo(int page) {
		int total = find.where().eq("deleteFlag", false).findRowCount();
		int start = (page - 1) * Constants.AMOUNT_PER_PAGE + 1;
		int end = start + Constants.AMOUNT_PER_PAGE;

		return new PageInfo(page, start, end, total);
	}

	public static AppModel findByPkg(String pkgName) {
		return find.where().eq("packageName", pkgName).findUnique();
	}

	public static String findMarketAppName() {
		AppModel app = findByPkg(Constants.MARKET_PACKAGE_NAME);
		if (app != null) {
			return Constants.MARKET_PACKAGE_NAME + Constants.NAME_DELIMITOR + app.appVersionCode
					+ ".apk";
		} else {
			return "";
		}
	}
	
	public static void downloadCount(String filename) {
		if(filename ==  null) return;
		int pos = filename.indexOf(Constants.NAME_DELIMITOR);
		if(pos > 0){
			String pkgName = filename.substring(0, pos);
			AppModel app = findByPkg(pkgName);
			app.downloads = app.downloads + 1;
			app.update();
		}
	}

	@Override
	public String toString() {
		return "APP [name:" + appname + "]";
	}

	public static boolean isOwner(Long app, String username) {
		return find.where().eq("author.name", username).eq("id", app)
				.findRowCount() > 0;
	}
}
