package aiss.api.consume;

<<<<<<< HEAD

import java.util.Arrays;
import java.util.Collection;

import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;



public class PilotoResource {

	private String uri = "https://api-aiss.ey.r.appspot.com/";
	//private String uri = "http://localhost:8095/api/songs";

	
	public Collection<Piloto> getAll() {
		ClientResource cr = null;
		Piloto [] songs = null;
		try {
			cr = new ClientResource(uri);
			songs = cr.get(Piloto[].class);
			
		} catch (ResourceException re) {
			System.err.println("Error when retrieving all songs: " + cr.getResponse().getStatus());
			throw re;
		}
		
		return Arrays.asList(songs);
	}
	

	public Piloto getSong(String pilotoId) {
		//TODO
		return null;
	}
	

	public Piloto addSong(Song piloto) {
		// TODO
		return null;

	}
	
	public boolean updatePiloto(Piloto piloto) {
		// TODO
		return false;
	}
	

	public boolean deletePiloto(String pilotoId) {
		ClientResource cr = null;
		boolean success = true;
		try {
			cr = new ClientResource(uri + "/" + pilotoId);
			cr.setEntityBuffering(true);		// Needed for using RESTlet from JUnit tests
			cr.delete();
			
		} catch (ResourceException re) {
			System.err.println("Error when deleting the piloto: " + cr.getResponse().getStatus());
			success = false;
			throw re;
		}
		
		return success;
		
	}
}
=======
import java.net.URI;
import java.net.URISyntaxException;
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
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriInfo;

import org.jboss.resteasy.spi.BadRequestException;
import org.jboss.resteasy.spi.NotFoundException;

import aiss.comparator.ComparatorAgePilotos;
import aiss.comparator.ComparatorAgePilotosReversed;
import aiss.comparator.ComparatorNamePiloto;
import aiss.comparator.ComparatorNamePilotoReversed;
import aiss.comparator.ComparatorVictoriesPilotos;
import aiss.comparator.ComparatorVictoriesPilotosReversed;
import aiss.model.Piloto;
import aiss.model.repository.CircuitoRepositorio;
import aiss.model.repository.MapCircuitoRepositorio;

@Path("/pilotos")
public class PilotoResource {

	public static PilotoResource _instance=null;
	CircuitoRepositorio repository;

	private RecursoPiloto(){
		repository=MapCircuitoRepositorio.getInstance();
	}

	public static PilotoResource getInstance()
	{
		if(_instance==null)
			_instance=new PilotoResource();
		return _instance; 
	}

	//OBTIENE TODOS LOS PILOTOS
	@GET
	@Produces("application/json")
	public Collection<Piloto> getAll(
			@QueryParam("escuderia")String escuderia,
			@QueryParam("nombre")String nombre,
			@QueryParam("edad")Integer edad ,
			@QueryParam("dorsal")Integer dorsal, 
			@QueryParam("victorias")Integer victorias,
			@QueryParam("limit")Integer limit,
			@QueryParam("offset")Integer offset,
			@QueryParam("order")String order) {

		List<Piloto> result = new ArrayList<Piloto>();
		for(Piloto piloto: repository.getAllPilotos()) {

			if((nombre==null || nombre.equals(piloto.getNombre())) &&	//FILTROS (NO ENUMERADOS)
					(escuderia==null || escuderia.equals(piloto.getEscuderia())) &&		//MIRA CADA UNO DE LOS PARAMETROS 
					(edad==null || edad.equals(piloto.getEdad()))&&		//SI NINGUN PARAMETRO ENCUENTRA ALGUNA IGUALDAD DEVUELVE LA LISTA ENTERA
					(dorsal==null || dorsal.equals(piloto.getDorsal())) &&
					(victorias == null || victorias.equals(piloto.getVictorias()))) {

				result.add(piloto);


			}

			if(limit!=null) {
				result = result.stream().limit(limit).collect(Collectors.toList());
			}


			//ORDER

			//NAME
			if(order != null) {
				if(order.equals("name")) {
					Collections.sort(result, new ComparatorNamePiloto());
				}else if(order.equals("-name")) {
					Collections.sort(result, new ComparatorNamePilotoReversed());
				}

				//EDAD


				else if(order.equals("age")) {
					Collections.sort(result, new ComparatorAgePilotos());
				}else if(order.equals("-age")) {
					Collections.sort(result, new ComparatorAgePilotosReversed());
				}

				//VICTORIAS


				else if(order.equals("victories")) {
					Collections.sort(result, new ComparatorVictoriesPilotos());
				}else if(order.equals("-victories")) {
					Collections.sort(result, new ComparatorVictoriesPilotosReversed());
				}


				//DORSAL


				else if(order.equals("number")) {
					Collections.sort(result, new ComparatorVictoriesPilotos());
				}else if(order.equals("-number")) {
					Collections.sort(result, new ComparatorVictoriesPilotosReversed());
				}else {
					throw new BadRequestException("The order parameter must be number,age,victories,name or the parameters described before with '-'");
				}
			}










		}
		//OFFSET

		List<Piloto> resultOffset = new ArrayList<Piloto>();
		if(offset!=null && offset>0) {

			for (int i= offset; i< result.size(); i++) {
				Piloto p = result.get(i);
				resultOffset.add(p);

			}
			result = resultOffset;
		}

		return result;
	}

	//OBTIENE UN PILOTO POR SU ID
	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Piloto get(@PathParam("id") String pilotoId)
	{
		Piloto piloto= repository.getPiloto(pilotoId);

		if(piloto == null) {
			throw new NotFoundException("The pilot with id = "+pilotoId+"was not found");
		}
		return piloto;
	}

	//AÑADE UN PILOTO
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response addPiloto(@Context UriInfo uriInfo, Piloto piloto) {
		if(piloto.getEscuderia()==null || "" .equals(piloto.getEscuderia())) {
			throw new BadRequestException("The name of the team must not be null");
		}
		if(piloto.getNombre()==null || "" .equals(piloto.getNombre())) {
			throw new BadRequestException("The name must not be null");
		}
		if(piloto.getDorsal()==null || "" .equals(piloto.getDorsal())) {
			throw new BadRequestException("The number of the pilot must not be null");
		}


		repository.addPiloto(piloto);
		ResponseBuilder rb = null;
		try {
			rb = Response.created(new URI("/pilotos/" + piloto.getId()));
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		rb.entity(piloto);
		return rb.build();
	}

	//ACTUALIZA UN PILOTO
	@PUT
	@Path("/{id}")
	@Consumes("application/json")
	public Response updatePiloto(@PathParam("id") String pilotoId, Piloto piloto) {
		Piloto oldpiloto = repository.getPiloto(pilotoId);
		if(pilotoId == null) {
			throw new NotFoundException("The pilot with id="+ pilotoId +" was not found");	
		}else {
			//Update name
			if (piloto.getNombre()!=null)
				oldpiloto.setNombre(piloto.getNombre());
			//Update team
			if (piloto.getEscuderia()!=null)
				oldpiloto.setEscuderia(piloto.getEscuderia());
			//Update victorias
			if (piloto.getVictorias()!=null)
				oldpiloto.setVictorias(piloto.getVictorias());
			//Update edad
			if (piloto.getEdad()!=null)
				oldpiloto.setEdad(piloto.getEdad());

			repository.updatePiloto(oldpiloto);
		}
		//Can update dorsal
		if(piloto.getDorsal()!=null) {
			new BadRequestException("The dorsal property is not editable");
		}



		return Response.noContent().build();

		/*TAMBIEN SE PUEDE EVITAR EL RETURN PONIENDO
		 *  VOID COMO PARAMETRO DE SALIDA EN EL METODO*/
	}

	//BORRA UN PILOTO POR SU ID
	@DELETE
	@Path("/{id}")
	public Response removePiloto(@PathParam("id") String pilotoId) {
		Piloto toberemoved=repository.getPiloto(pilotoId);
		if (toberemoved == null)
			throw new NotFoundException("The pilot with id="+ pilotoId +" was not found");
		else
			repository.deletePiloto(pilotoId);

		return Response.noContent().build();
	}

	//OBTIENE TODOS LOS PILOTOS DE UN CIRCUITO
	@Path("/{pilotoId}/{circuitoId}")
	@Produces("application/json")
	public Collection<Piloto> getAll(@PathParam("id")String circuitoId){
		return repository.getAllPilotosCircuito(circuitoId);	}

}
>>>>>>> b6d44fac4248d100b2767f31c638de5b808315da
