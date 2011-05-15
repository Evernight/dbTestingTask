import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Main class
 */
public class DatabaseTestRunner {
	private static Logger log = Logger.getLogger(DatabaseTestRunner.class);

	public static void main(String[] args) {
		ApplicationContext appContext = new ClassPathXmlApplicationContext("beans.xml");
		CarAdsDatabase cassandra = (CarAdsDatabase) appContext.getBean("cassandraDB");

		log.info("Execution of main script finished");
	}

}
