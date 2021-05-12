package aiss.model;

import java.util.ArrayList;
import java.util.List;

public class CarDealership {

	private String id;
	private String name;
	private String city;
	private String postalCode;
	private List<Car> cars;
	
	public CarDealership() {}
	
	public CarDealership(String name) {
		this.name = name;
	}
	
	protected void setCars(List<Car> c) {
		cars = c;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public List<Car> getCars() {
		return cars;
	}
	
	public Car getCar(String id) {
		if (cars==null)
			return null;
		
		Car car =null;
		for(Car c: cars)
			if (c.getId().equals(id))
			{
				car=c;
				break;
			}
		return car;
	}
	
	public void addCar(Car c) {
		if (cars==null)
			cars = new ArrayList<Car>();
		cars.add(c);
	}
	
	public void deleteCar(Car c) {
		cars.remove(c);
	}
	
	public void deleteCar(String id) {
		Car c = getCar(id);
		if (c!=null)
			cars.remove(c);
	}
}
