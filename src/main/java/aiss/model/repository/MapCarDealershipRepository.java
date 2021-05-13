package aiss.model.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import aiss.model.CarDealership;
import aiss.model.Car;


public class MapCarDealershipRepository implements CarDealershipRepository{

	Map<String, CarDealership> carDealershipMap;
	Map<String, Car> carMap;
	private static MapCarDealershipRepository instance=null;
	private int index=0;			
	
	
	public static MapCarDealershipRepository getInstance() {
		
		if (instance==null) {
			instance = new MapCarDealershipRepository();
			instance.init();
		}
		
		return instance;
	}
	
	public void init() {
		
		carDealershipMap = new HashMap<String,CarDealership>();
		carMap = new HashMap<String,Car>();
		
		// Create cars
		Car seatIbiza=new Car();
		seatIbiza.setBrand("Seat");
		seatIbiza.setModel("Ibiza");
		seatIbiza.setLicensePlate("3003FML");
		seatIbiza.setYear("2015");
		seatIbiza.setColour("Blanco");
		addCar(seatIbiza);
		
		Car fiat500=new Car();
		fiat500.setBrand("Fiat");
		fiat500.setModel("500");
		fiat500.setLicensePlate("2742DJD");
		fiat500.setYear("2010");
		fiat500.setColour("Rojo");
		addCar(fiat500);
		
		Car mercedesBenzGLC=new Car();
		mercedesBenzGLC.setBrand("Mercedes-Benz");
		mercedesBenzGLC.setModel("GLC");
		mercedesBenzGLC.setLicensePlate("3740JJJ");
		mercedesBenzGLC.setYear("2019");
		mercedesBenzGLC.setColour("Negro");
		addCar(mercedesBenzGLC);
		
		Car audiA3=new Car();
		audiA3.setBrand("Audi");
		audiA3.setModel("A3");
		audiA3.setLicensePlate("3785HGJ");
		audiA3.setYear("2016");
		audiA3.setColour("Negro");
		addCar(audiA3);
		
		Car peugeotPartner=new Car();
		peugeotPartner.setBrand("Peugeot");
		peugeotPartner.setModel("Partner");
		peugeotPartner.setLicensePlate("5027DDT");
		peugeotPartner.setYear("2006");
		peugeotPartner.setColour("Gris");
		addCar(peugeotPartner);
		
		// Create carDealership
		CarDealership c1=new CarDealership();
		c1.setName("Concesionario Pino Montano");
		c1.setCity("SEVILLA");
		c1.setPostalCode("41015");
		addCarDealership(c1);
		
		CarDealership c2 = new CarDealership();
		c2.setName("Concesionario Viapol");
		c2.setCity("SEVILLA");
		c2.setPostalCode("41013");
		addCarDealership(c2);
		
		CarDealership c3 = new CarDealership();
		c3.setName("Concesionario San Sebastián de los Reyes");
		c3.setCity("MADRID");
		c3.setPostalCode("28701");
		addCarDealership(c3);
		
		// Add cars to carDealerships
		addCar(c1.getId(), fiat500.getId());
		addCar(c1.getId(), audiA3.getId());
		addCar(c1.getId(), peugeotPartner.getId());
		addCar(c1.getId(), mercedesBenzGLC.getId());
		addCar(c2.getId(), seatIbiza.getId());
		addCar(c2.getId(), audiA3.getId());
		addCar(c3.getId(), audiA3.getId());
		addCar(c3.getId(), fiat500.getId());
		addCar(c3.getId(), mercedesBenzGLC.getId());
	}
	
	// CarDealership related operations
	@Override
	public void addCarDealership(CarDealership cd) {
		String id = "cd" + index++;	
		cd.setId(id);
		carDealershipMap.put(id,cd);
	}
	
	@Override
	public Collection<CarDealership> getAllCarDealerships() {
			return carDealershipMap.values();
	}

	@Override
	public CarDealership getCarDealership(String id) {
		return carDealershipMap.get(id);
	}
	
	@Override
	public void updateCarDealership(CarDealership p) {
		carDealershipMap.put(p.getId(),p);
	}

	@Override
	public void deleteCarDealership(String id) {	
		carDealershipMap.remove(id);
	}
	

	@Override
	public void addCar(String carDealershipId, String carId) {
		CarDealership carDealership = getCarDealership(carDealershipId);
		carDealership.addCar(carMap.get(carId));
	}

	@Override
	public Collection<Car> getAll(String carDealershipId) {
		return getCarDealership(carDealershipId).getCars();
	}

	@Override
	public void removeCar(String carDealershipId, String carId) {
		getCarDealership(carDealershipId).deleteCar(carId);
	}

	
	// Song related operations
	
	@Override
	public void addCar(Car c) {
		String id = "c" + index++;
		c.setId(id);
		carMap.put(id, c);
	}
	
	@Override
	public Collection<Car> getAllCars() {
			return carMap.values();
	}

	@Override
	public Car getCar(String carId) {
		return carMap.get(carId);
	}

	@Override
	public void updateCar(Car c) {
		Car car = carMap.get(c.getId());
		car.setBrand(c.getBrand());
		car.setColour(c.getColour());
		car.setLicensePlate(c.getLicensePlate());
		car.setYear(c.getYear());
		car.setModel(c.getModel());
	}

	@Override
	public void deleteCar(String carId) {
		carMap.remove(carId);
	}
	
}
