package aiss.model.repository;

import java.util.Collection;

import aiss.model.CarDealership;
import aiss.model.Car;

public interface CarDealershipRepository {
	
		//Cars
		public void addCar(Car c);
		public Collection<Car> getAllCars();
		public Car getCar(String carId);
		public void updateCar(Car c);
		public void deleteCar(String carId);
		
		//CarDealerships
		public void addCarDealership(CarDealership cd);
		public Collection<CarDealership> getAllCarDealerships();
		public CarDealership getCarDealership(String carDealershipId);
		public void updateCarDealership(CarDealership cd);
		public void deleteCarDealership(String carDealershipId);
		public Collection<Car> getAll(String carDealershipId);
		public void addCar(String carDealershipId, String carId);
		public void removeCar(String carDealershipId, String carId); 

}
