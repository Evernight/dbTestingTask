import java.util.List;
import me.prettyprint.cassandra.serializers.IntegerSerializer;
import me.prettyprint.cassandra.serializers.StringSerializer;
import me.prettyprint.hector.api.Keyspace;
import me.prettyprint.hector.api.ResultStatus;
import me.prettyprint.hector.api.beans.ColumnSlice;
import me.prettyprint.hector.api.factory.HFactory;
import me.prettyprint.hector.api.mutation.Mutator;
import me.prettyprint.hector.api.query.ColumnQuery;
import me.prettyprint.hector.api.query.QueryResult;
import me.prettyprint.hector.api.query.SliceQuery;
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
		SliceQuery query = HFactory.createSliceQuery(keyspace, integerSerializer, stringSerializer, stringSerializer);
		query.setColumnFamily(CassandraConfigurator.BY_ID_COLUMN_FAMILY)
		.setKey(id)
		.setRange("", "", false, 10);

		QueryResult result = query.execute();

		return CarAdvertisementCassandra.fromColumnSlice((ColumnSlice) result.get());
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
			CarAdvertisementCassandra.appendMutatorWithAd(mutator, item);
		}
		log.info(String.format("Cassandra DB: initiated insertion of %d records", list.size()));
		mutator.execute();
		log.info(String.format("Cassandra DB: finished insertion of records"));
	}

	public void exportToFile(String filename) {
	}

	public void clearDatabase() {
		Mutator<Integer> mutator = HFactory.createMutator(keyspace, integerSerializer);
		// TODO write
	}

}
