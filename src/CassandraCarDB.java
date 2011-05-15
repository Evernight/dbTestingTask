import java.util.List;

/**
 * Cassandra part
 */
public class CassandraCarDB implements CarAdsDatabase {
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
