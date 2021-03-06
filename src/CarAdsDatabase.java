import java.io.IOException;
import java.util.List;

/**
 * Interface to work with car advertisement database
 */

public interface CarAdsDatabase {
 	public CarAdvertisement getByID(int id);
	public List<CarAdvertisement> getSortedByDate(int count);
	public List<CarAdvertisement> getSortedByPrice(int count);
	public List<CarAdvertisement> getOnlySelectedModel(String model, int count);
	public void addRows(List<CarAdvertisement> list);
	public void exportToFile(String filename);
	public void clearDatabase() throws IOException;
}
