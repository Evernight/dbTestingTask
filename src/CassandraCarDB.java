import java.util.List;
import me.prettyprint.hector.api.Keyspace;
import org.apache.log4j.Logger;

/**
 * Cassandra part
 */
public class CassandraCarDB implements CarAdsDatabase {
	private static Logger log = Logger.getLogger(CassandraCarDB.class);

	private Keyspace keyspace;

	public CassandraCarDB(Keyspace keyspace) {
		this.keyspace = keyspace;
		log.debug("Created CassandraCarDB instance");
	}

	public CarAdvertisement getByID(int id) {
		return null;
	}

	public List<CarAdvertisement> getSortedByDate(int count) {
		return null;
	}

	public List<CarAdvertisement> getSortedByPrice(int count) {
		return null;
	}

	public List<CarAdvertisement> getOnlySelectedModel(String model, int count) {
		return null;
	}

	public void exportToFile(String filename) {
	}

}
