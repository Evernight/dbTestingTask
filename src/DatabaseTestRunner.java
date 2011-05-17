import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.io.IOException;
import java.util.List;

/**
 * Main class
 */
public class DatabaseTestRunner {
	// TODO more tests
	private static Logger log = Logger.getLogger(DatabaseTestRunner.class);

	@Test
	public static void RunSmallTest(CarAdsDatabase db) throws IOException {
		List<CarAdvertisement> data = PlainTextCarDBDump.loadFromFile("resources/handmade.dump");

		db.addRows(data);

		Assert.assertEquals("Black", db.getByID(0).color);
		List<CarAdvertisement> r = db.getSortedByDate(10);
		Assert.assertEquals(4, r.size());

		db.clearDatabase();
	}

	public static void main(String[] args) throws IOException {
		ApplicationContext appContext = new ClassPathXmlApplicationContext("beans.xml");
		CarAdsDatabase cassandra = (CarAdsDatabase) appContext.getBean("cassandraDB");

		RunSmallTest(cassandra);

		((ConfigurableApplicationContext) appContext).close();
		log.info("Execution of main script finished");
	}

}
