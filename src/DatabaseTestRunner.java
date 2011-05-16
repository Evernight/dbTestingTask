import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.io.IOException;
import java.util.List;

/**
 * Main class
 */
public class DatabaseTestRunner {
	private static Logger log = Logger.getLogger(DatabaseTestRunner.class);

	public static void RunTest(CarAdsDatabase db) throws IOException {
		List<CarAdvertisement> data = PlainTextCarDBDump.loadFromFile("resources/handmade.dump");
		db.addRows(data);
		log.debug(db.getByID(1).color);
		db.clearDatabase();
	}

	public static void main(String[] args) throws IOException {
		ApplicationContext appContext = new ClassPathXmlApplicationContext("beans.xml");
		CarAdsDatabase cassandra = (CarAdsDatabase) appContext.getBean("cassandraDB");

		RunTest(cassandra);

		log.info("Execution of main script finished");
	}

}
