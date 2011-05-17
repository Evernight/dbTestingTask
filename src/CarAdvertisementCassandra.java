import me.prettyprint.cassandra.serializers.DoubleSerializer;
import me.prettyprint.cassandra.serializers.IntegerSerializer;
import me.prettyprint.cassandra.serializers.StringSerializer;
import me.prettyprint.hector.api.beans.ColumnSlice;
import me.prettyprint.hector.api.beans.OrderedRows;
import me.prettyprint.hector.api.beans.Row;
import me.prettyprint.hector.api.factory.HFactory;
import me.prettyprint.hector.api.mutation.Mutator;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Class contains functions needed to convert CarAdvertisement between Cassandra DB record
 * and actual java object
 */
public  class CarAdvertisementCassandra {
	private static Logger log = Logger.getLogger(CarAdvertisementCassandra.class);

	private static StringSerializer stringSerializer = new StringSerializer();
	private static IntegerSerializer integerSerializer = new IntegerSerializer();
	private static DoubleSerializer doubleSerializer = new DoubleSerializer();

	public static void appendMutatorWithAd(Mutator<Integer> mutator, CarAdvertisement ad) {
		mutator.addInsertion(
				ad.id, CassandraConfigurator.BY_ID_COLUMN_FAMILY,
				HFactory.createStringColumn("id", String.valueOf(ad.id)));
		mutator.addInsertion(
				ad.id, CassandraConfigurator.BY_ID_COLUMN_FAMILY,
				HFactory.createStringColumn("model", ad.model));
		mutator.addInsertion(
				ad.id, CassandraConfigurator.BY_ID_COLUMN_FAMILY,
				HFactory.createStringColumn("car_class", ad.car_class));
		mutator.addInsertion(
				ad.id, CassandraConfigurator.BY_ID_COLUMN_FAMILY,
				HFactory.createStringColumn("color", ad.color));
		mutator.addInsertion(
				ad.id, CassandraConfigurator.BY_ID_COLUMN_FAMILY,
				HFactory.createStringColumn("year", String.valueOf(ad.year)));
		mutator.addInsertion(
				ad.id, CassandraConfigurator.BY_ID_COLUMN_FAMILY,
				HFactory.createStringColumn("condition", ad.condition));
		mutator.addInsertion(
				ad.id, CassandraConfigurator.BY_ID_COLUMN_FAMILY,
				HFactory.createStringColumn("price", String.valueOf(ad.price)));
		mutator.addInsertion(
				ad.id, CassandraConfigurator.BY_ID_COLUMN_FAMILY,
				HFactory.createStringColumn("contact", ad.contact));
		mutator.addInsertion(
				ad.id, CassandraConfigurator.BY_ID_COLUMN_FAMILY,
				HFactory.createStringColumn("date", ad.date));
		mutator.addInsertion(
				ad.id, CassandraConfigurator.BY_ID_COLUMN_FAMILY,
				HFactory.createStringColumn("rating", String.valueOf(ad.rating)));
	}

	public static CarAdvertisement fromColumnSlice(ColumnSlice<String, String> slice) {
		return new CarAdvertisement(
				Integer.valueOf((String) slice.getColumnByName("id").getValue()),
				(String) slice.getColumnByName("model").getValue(),
				(String) slice.getColumnByName("car_class").getValue(),
				(String) slice.getColumnByName("color").getValue(),
				Integer.valueOf((String) slice.getColumnByName("year").getValue()),
				(String) slice.getColumnByName("condition").getValue(),
				Integer.valueOf((String) slice.getColumnByName("price").getValue()),
				(String) slice.getColumnByName("contact").getValue(),
				(String) slice.getColumnByName("date").getValue(),
				Double.valueOf((String) slice.getColumnByName("rating").getValue()));
	}

	public static List<CarAdvertisement> fromOrderedRows(OrderedRows<Integer, String, String> rows) {
		List<CarAdvertisement> result = new ArrayList<CarAdvertisement>();
		for (Row<Integer, String, String> row : rows.getList()) {
				result.add(fromColumnSlice(row.getColumnSlice()));
		}

		return result;
	}

}
