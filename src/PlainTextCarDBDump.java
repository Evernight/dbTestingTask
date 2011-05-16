import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to load data from plain text file
 */
public class PlainTextCarDBDump {
	public static String DELIMETER = "\t";

	private static CarAdvertisement parseRecord(String s) throws IOException {
		String[] items = s.split(DELIMETER);
		if (items.length != 10)
			throw new IOException();
		return new CarAdvertisement(Integer.valueOf(items[0]), items[1], items[2], items[3],
				Integer.valueOf(items[4]), items[5], Integer.valueOf(items[6]),
				items[7], items[8], Double.valueOf(items[9]));

	}

	public static List<CarAdvertisement> loadFromFile(String fileName) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(fileName));

		ArrayList<CarAdvertisement> result = new ArrayList<CarAdvertisement>();

		while (true) {
			String next = in.readLine();
			if (next == null)
				break;
			result.add(parseRecord(next));
		}

		return result;
	}
}
