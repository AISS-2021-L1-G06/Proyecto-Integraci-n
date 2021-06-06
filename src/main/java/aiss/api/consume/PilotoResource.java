package aiss.api.consume;


import java.util.Arrays;
import java.util.Collection;

import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;


public class PilotoResource {

	private String uri = "https://api-aiss.ey.r.appspot.com/api/pilotos";
	//private String uri = "http://localhost:8095/api/songs";

	
	public Collection<Piloto> getAll() {
		ClientResource cr = null;
		Piloto [] pilotos = null;
		try {
			cr = new ClientResource(uri);
			pilotos = cr.get(Piloto[].class);
			
		} catch (ResourceException re) {
			System.err.println("Error cuando obtienes todos los pilotos: " + cr.getResponse().getStatus());
			throw re;
		}
		
		return Arrays.asList(pilotos);
	}
	

	public Piloto getPiloto(String pilotoId) {
		ClientResource cr = null;
		Piloto piloto = null;
		try {
			cr = new ClientResource(uri + "/" + pilotoId);
			piloto = cr.get(Piloto.class);
			
		} catch (ResourceException re) {
			System.err.println("Error cuando obtienes el piloto: " + cr.getResponse().getStatus());
		}
		
		return piloto;
	}
	

	public Piloto addPiloto(Piloto piloto) {
		ClientResource cr = new ClientResource(uri);
		Piloto result = null;
		try { 
			result = cr.post(piloto, Piloto.class);
		} catch (ResourceException re) {
			System.err.println("Error cuando añades el piloto: " + cr.getResponse().getStatus());

		}
		return result;


	}
	
	public boolean updatePiloto(Piloto piloto) {
		boolean result = false;
		ClientResource cr = new ClientResource(uri);
		try {
			cr.put(piloto);	
			result = true;
		} catch (ResourceException re){
			System.err.println("Error cuando modificas al piloto: " + cr.getResponse().getStatus());

		}
		
		return result;
	}
	

	public boolean deletePiloto(String pilotoId) {
		ClientResource cr = null;
		boolean success = true;
		try {
			cr = new ClientResource(uri + "/" + pilotoId);
			cr.setEntityBuffering(true);		// Needed for using RESTlet from JUnit tests
			cr.delete();
			
		} catch (ResourceException re) {
			System.err.println("Error cuando borras al piloto " + cr.getResponse().getStatus());
			success = false;
			throw re;
		}
		
		return success;
		
	}
}
