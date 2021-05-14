package aiss.api.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.jboss.resteasy.spi.BadRequestException;
import org.jboss.resteasy.spi.NotFoundException;

import aiss.model.Car;
import aiss.model.CarDealership;
import aiss.model.repository.CarDealershipRepository;
import aiss.model.repository.MapCarDealershipRepository;
import comparator.ComparatorNameCarDealership;
import comparator.ComparatorNameCarDealershipReversed;


@Path("/carDealership")
public class CarDealershipResource {
	
	/* Singleton */
	private static CarDealershipResource _instance=null;
	CarDealershipRepository repository;
	
	private CarDealershipResource() {
		repository=MapCarDealershipRepository.getInstance();

	}
	
	public static CarDealershipResource getInstance()
	{
		if(_instance==null)
				_instance=new CarDealershipResource();
		return _instance;
	}
	

//	@GET
//	@Produces("application/json")
//	public Collection<CarDealership> getAll()
//	{
//		return repository.getAllCarDealerships();
//	}
	
	@GET
	@Produces("application/json")
	public Collection<CarDealership> getAll
		(@QueryParam("isEmpty")Boolean isEmpty,
			@QueryParam("name")String name,
			@QueryParam("city")String city,
			@QueryParam("postalCode")String code,
			@QueryParam("order")String order,
			@QueryParam("limit")Integer limit) {

		List<CarDealership> res = new ArrayList<>();
		for(CarDealership carDealership: repository.getAllCarDealerships()) {

			if((name==null || name.equals(carDealership.getName())) &&	//FILTROS (NO ENUMERADOS)
					(city==null || city.equals(carDealership.getCity()))&&		
					(code==null || code.equals(carDealership.getPostalCode())))	{

				if(isEmpty==null ||//FILTRO (ENUMERADO) 
						(isEmpty&&(carDealership.getCars() == null || carDealership.getCars().size()==0)) ||
						(!isEmpty&&(carDealership.getCars() != null || carDealership.getCars().size()>0))) {
					res.add(carDealership);
				}
				
			}
			
			
			if(limit!=null) {
				res = res.stream().limit(limit).collect(Collectors.toList());
			}
			
			

			if(order != null) {
				if(order.equals("name")) {
					Collections.sort(res, new ComparatorNameCarDealership());
				}else if(order.equals("-name")) {
					Collections.sort(res, new ComparatorNameCarDealershipReversed());
				}else {
					throw new BadRequestException("The order parameter must be name or -name");
				}


			}

		}
		return res;
	}
	
	
	@GET
	@Path("/{id}")
	@Produces("application/json")
	public CarDealership get(@PathParam("id") String id)
	{
		CarDealership list = repository.getCarDealership(id);
		
		if (list == null) {
			throw new NotFoundException("The playlist with id="+ id +" was not found");			
		}
		
		return list;
	}
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response addCarDealership(@Context UriInfo uriInfo, CarDealership carDealership) {
		if (carDealership.getName() == null || "".equals(carDealership.getName()))
			throw new BadRequestException("The name of the car dealership must not be null");
		
		if (carDealership.getCars()!=null)
			throw new BadRequestException("The cars property is not editable.");

		repository.addCarDealership(carDealership);

		// Builds the response. Returns the playlist the has just been added.
		UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(), "get");
		URI uri = ub.build(carDealership.getId());
		ResponseBuilder resp = Response.created(uri);
		resp.entity(carDealership);			
		return resp.build();
	}

	
	@PUT
	@Consumes("application/json")
	public Response updateCarDealership(CarDealership carDealership) {
		CarDealership oldCardDealership = repository.getCarDealership(carDealership.getId());
		if (oldCardDealership == null) {
			throw new NotFoundException("The playlist with id="+ carDealership.getId() +" was not found");			
		}
		
		if (carDealership.getCars()!=null)
			throw new BadRequestException("The cars property is not editable.");
		
		// Update name
		if (carDealership.getName()!=null)
			oldCardDealership.setName(carDealership.getName());
		
		// Update city
		if (carDealership.getCity()!=null)
			oldCardDealership.setCity(carDealership.getCity());
		
		//Update postal code
		if (carDealership.getPostalCode()!=null)
			oldCardDealership.setPostalCode(carDealership.getPostalCode());
		
		return Response.noContent().build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response removeCarDealership(@PathParam("id") String id) {
		CarDealership toberemoved=repository.getCarDealership(id);
		if (toberemoved == null)
			throw new NotFoundException("The car dealership with id="+ id +" was not found");
		else
			repository.deleteCarDealership(id);
		
		return Response.noContent().build();
	}
	
	
	@POST	
	@Path("/{carDealershipId}/{carId}")
	@Consumes("text/plain")
	@Produces("application/json")
	public Response addCar(@Context UriInfo uriInfo,@PathParam("carDealershipId") String carDealershipId, @PathParam("carId") String carId)
	{				
		
		CarDealership carDealership = repository.getCarDealership(carDealershipId);
		Car car = repository.getCar(carId);
		
		if (carDealership==null)
			throw new NotFoundException("The car dealership with id=" + carDealershipId + " was not found");
		
		if (car == null)
			throw new NotFoundException("The car with id=" + carId + " was not found");
		
		if (carDealership.getCar(carId)!=null)
			throw new BadRequestException("The car is already included in the car dealership.");
			
		repository.addCar(carDealershipId, carId);		

		// Builds the response
		UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(), "get");
		URI uri = ub.build(carDealershipId);
		ResponseBuilder resp = Response.created(uri);
		resp.entity(carDealership);			
		return resp.build();
	}
	
	
	@DELETE
	@Path("/{carDealershipId}/{carId}")
	public Response removeCar(@PathParam("carDealershipId") String carDealershipId, @PathParam("carId") String carId) {
		CarDealership carDealership = repository.getCarDealership(carDealershipId);
		Car car = repository.getCar(carId);
		
		if (carDealership==null)
			throw new NotFoundException("The car dealership with id=" + carDealershipId + " was not found");
		
		if (car == null)
			throw new NotFoundException("The car with id=" + carId + " was not found");
		
		
		repository.removeCar(carDealershipId, carId);		
		
		return Response.noContent().build();
	}

}
