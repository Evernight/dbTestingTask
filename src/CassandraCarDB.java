import java.util.List;
import me.prettyprint.cassandra.serializers.IntegerSerializer;
import me.prettyprint.cassandra.serializers.StringSerializer;
import me.prettyprint.hector.api.Keyspace;
import me.prettyprint.hector.api.factory.HFactory;
import me.prettyprint.hector.api.mutation.Mutator;
import org.apache.log4j.Logger;

/**
 * Cassandra part
 */
public class CassandraCarDB implements CarAdsDatabase {
	private static Logger log = Logger.getLogger(CassandraCarDB.class);

	private static StringSerializer stringSerializer = new StringSerializer();
	private static IntegerSerializer integerSerializer = new IntegerSerializer();

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

	public void addRows(List<CarAdvertisement> list) {
		Mutator<Integer> mutator = HFactory.createMutator(keyspace, integerSerializer);
		for (CarAdvertisement item : list) {
			mutator.addInsertion(item.id, "ads", HFactory.createStringColumn("color", item.color));
		}
		log.info(String.format("Cassandra DB: started insertion of %d records", list.size()));
		mutator.execute();
		log.info(String.format("Cassandra DB: finished insertion of records"));
	}

	public void exportToFile(String filename) {
	}

	public void clearDatabase() {
	}

}
