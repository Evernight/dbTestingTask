import java.io.IOException;
import java.util.List;
import me.prettyprint.cassandra.serializers.IntegerSerializer;
import me.prettyprint.cassandra.serializers.StringSerializer;
import me.prettyprint.hector.api.Cluster;
import me.prettyprint.hector.api.Keyspace;
import me.prettyprint.hector.api.beans.ColumnSlice;
import me.prettyprint.hector.api.beans.OrderedRows;
import me.prettyprint.hector.api.factory.HFactory;
import me.prettyprint.hector.api.mutation.Mutator;
import me.prettyprint.hector.api.query.QueryResult;
import me.prettyprint.hector.api.query.RangeSlicesQuery;
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
	private Cluster cluster;

	public CassandraCarDB(Cluster cluster) {
		this.cluster = cluster;
		this.keyspace = HFactory.createKeyspace(CassandraConfigurator.KEYSPACE_NAME, cluster);

		log.debug("Created CassandraCarDB instance");
	}

	public CarAdvertisement getByID(int id) {
		SliceQuery<Integer, String, String> query= HFactory.createSliceQuery(keyspace, integerSerializer, stringSerializer, stringSerializer);
		query.setColumnFamily(CassandraConfigurator.BY_ID_COLUMN_FAMILY)
		.setKey(id)
		.setRange("", "", false, 10);

		QueryResult<ColumnSlice<String, String>> result = query.execute();

		return CarAdvertisementCassandra.fromColumnSlice(result.get());
	}

	public List<CarAdvertisement> getSortedByDate(int count) {
		RangeSlicesQuery<Integer, String, String> query = HFactory.createRangeSlicesQuery(
				keyspace, integerSerializer, stringSerializer, stringSerializer);
		query.setColumnFamily(CassandraConfigurator.BY_ID_COLUMN_FAMILY)
		.setRowCount(count)
		.setRange("", "", false, 10);

		log.info(String.format("Request for %d items sorted by date", count));
		QueryResult<OrderedRows<Integer, String, String>> result = query.execute();
		log.info("Items extraction finished");

		return CarAdvertisementCassandra.fromOrderedRows(result.get());
	}

	public List<CarAdvertisement> getSortedByPrice(int count) {
		// TODO implement using additional column family
		return null;
	}

	public List<CarAdvertisement> getOnlySelectedModel(String model, int count) {
		// TODO implement using secondary index
		return null;
	}

	public void addRows(List<CarAdvertisement> list) {
		Mutator<Integer> mutator = HFactory.createMutator(keyspace, integerSerializer);
		for (CarAdvertisement item : list) {
			CarAdvertisementCassandra.appendMutatorWithAd(mutator, item);
		}
		log.info(String.format("Initiated insertion of %d records", list.size()));
		mutator.execute();
		log.info(String.format("Finished insertion of records"));
	}

	public void exportToFile(String filename) {
	}

	public void clearDatabase() throws IOException {
		log.info("Clearing database...");
		cluster.truncate(CassandraConfigurator.KEYSPACE_NAME, CassandraConfigurator.BY_ID_COLUMN_FAMILY);
		log.info("Database cleared");
	}

}
