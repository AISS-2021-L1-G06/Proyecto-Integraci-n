package aiss.model;

public class Car {
	
	private String id;
	private String brand;
	private String model;
	private String licensePlate;
	private String year;
	private String colour;

	public Car() {}

	public Car(String brand, String model, String licensePlate, String year, String colour) {
		this.brand = brand;
		this.model = model;
		this.licensePlate = licensePlate;
		this.year = year;
		this.colour= colour;
	}
	
	public Car(String id, String brand, String model, String licensePlate, String year, String colour) {
		super();
		this.id = id;
		this.brand = brand;
		this.model = model;
		this.licensePlate = licensePlate;
		this.year = year;
		this.colour = colour;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	

}
