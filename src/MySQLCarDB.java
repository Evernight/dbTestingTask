import java.util.List;

/**
 * MySQL part
 */
public class MySQLCarDB implements CarAdsDatabase {
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
	}

	public void exportToFile(String filename) {
	}

	public void clearDatabase() {
	}
}
