import play.*;
import play.api.mvc.EssentialFilter;
import play.libs.*;

import java.util.*;
import java.util.concurrent.*;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import scala.concurrent.duration.Duration;

import com.avaje.ebean.*;

import models.*;
import play.filters.gzip.GzipFilter;

public class Global extends GlobalSettings {

	public <T extends EssentialFilter> Class<T>[] filters() {
		return new Class[] { GzipFilter.class };
	}

	public void onStart(Application app) {
		InitialData.insert(app);

	}

	static class InitialData {

		public static void insert(Application app) {
			if (Ebean.find(CategoryModel.class).findRowCount() == 0) {

				Map<String, List<Object>> all = (Map<String, List<Object>>) Yaml
						.load("initial-data.yml");

				// Insert users first
				Ebean.save(all.get("categories"));

			}
		}

	}
}

