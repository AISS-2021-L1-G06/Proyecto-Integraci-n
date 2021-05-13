package aiss.api.resources;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import org.jboss.resteasy.spi.BadRequestException;

import aiss.model.Car;
import aiss.model.repository.CarDealershipRepository;
import aiss.model.repository.MapCarDealershipRepository;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriBuilder;


import javassist.NotFoundException;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;



@Path("/cars")
public class CarResource {

	public static CarResource _instance=null;
	CarDealershipRepository repository;
	
	private CarResource(){
		repository=MapCarDealershipRepository.getInstance();
	}
	
	public static CarResource getInstance()
	{
		if(_instance==null)
			_instance=new CarResource();
		return _instance; 
	}
	
	@GET
	@Produces("application/json")
	public Collection<Car> getAll()
	{
		return repository.getAllCars();
	}
	
	
	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Car get(@PathParam("id") String carId) throws NotFoundException
	{
		Car s = repository.getCar(carId);
		
		if(s == null) {
			throw new NotFoundException("The song with id="+ carId +" was not found");
		}
		
		return s;
	}
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response addSong(@Context UriInfo uriInfo, Car car) {
		
		if(car.getModel()==null || "".equals(car.getModel())) {
			throw new BadRequestException("El nombre de la cancion no puede ser null");
		}
		
		repository.addCar(car);
		
		//Builds the response. Returns the song that has been added.
		ResponseBuilder resp = null;
		try {
			resp = Response.created(new URI("/cars/"+car.getId()));
		}catch(URISyntaxException e){
			e.printStackTrace();
		}
		resp.entity(car);
		return resp.build();
		
	}
	
	
	@PUT
	@Consumes("application/json")
	public Response updateSong(Car car) throws NotFoundException {
		
		Car oldCar = repository.getCar(car.getId());
		
		if(oldCar==null) {
			throw new NotFoundException("The car with id="+car.getId()+" was not found");
		}
		if(car.getModel()!=null) {
			oldCar.setModel(car.getModel());
		}
		if(car.getBrand()!=null) {
			oldCar.setBrand(car.getBrand());
		}
		if(car.getColour()!=null) {
			oldCar.setColour(car.getColour());
		}
		if(car.getLicensePlate()!=null) {
			oldCar.setLicensePlate(car.getLicensePlate());
		}
		if(car.getYear()!=null) {
			oldCar.setYear(car.getYear());
		}
		if(car.getId()!=null) {
			oldCar.setId(car.getId());
		}
		
		return Response.noContent().build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response removeSong(@PathParam("id") String carId) throws NotFoundException {
		
		Car toberemoved=repository.getCar(carId);
		if(toberemoved == null) {
			throw new NotFoundException("The song with id="+carId+" was not found");
		}
		else {
			repository.deleteCar(carId);
		}
		return Response.noContent().build();
	}
	
}
