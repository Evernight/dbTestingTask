import java.util.Date;

/**
 * Class to represent single advertisement
 */

public class CarAdvertisement {
	/*
	 * Advertisement fields
	 */
	public int id;
	public String model;
	public String car_class;
	public String color;
	public String condition;
	public int year;
	public int price;
	public String contact;
	public String date;
	public double rating;

	public CarAdvertisement(int id, String model, String car_class, String color, int year,
	                        String condition, int price, String contact, String date, double rating) {
		this.id = id;
		this.model = model;
		this.car_class = car_class;
		this.color = color;
		this.year = year;
		this.condition = condition;
		this.price = price;
		this.contact = contact;
		this.date = date;
		this.rating = rating;
	}
}
