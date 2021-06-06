package aiss.api.consume;


import java.util.Arrays;
import java.util.Collection;

import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

public class CircuitoResource {

	private String uri = "https://api-aiss.ey.r.appspot.com/api/circuitos";
	//private String uri = "http://localhost:8095/api/lists";
	

	public Collection<Circuito> getAll() {
		
		ClientResource cr = null;
		Circuito[] lists = null;
		try {
			cr = new ClientResource(uri);
			lists = cr.get(Circuito[].class);
			
		} catch (ResourceException re) {
			System.err.println("Error when retrieving the collections of playlists: " + cr.getResponse().getStatus());
		}
		
		return Arrays.asList(lists);
	}
	
	public Circuito getCircuito(String circuitoId) {
		
		ClientResource cr = null;
		Circuito list = null;
		try {
			cr = new ClientResource(uri + "/" + circuitoId);
			list = cr.get(Circuito.class);
			
		} catch (ResourceException re) {
			System.err.println("Error cuando obtienes el circuito" + cr.getResponse().getStatus());
		}
		
		return list;

	}
	

	public Circuito addCircuito(Circuito c) {
		
		ClientResource cr = null;
		Circuito resultCircuito = null;
		try {
			cr = new ClientResource(uri);
			cr.setEntityBuffering(true);		// Needed for using RESTlet from JUnit tests
			resultCircuito = cr.post(c,Circuito.class);
			
		} catch (ResourceException re) {
			System.err.println("Error cuando añades el circuito" + cr.getResponse().getStatus());
		}
		
		return resultCircuito;
	}
	

	public boolean updateCircuito(Circuito c) {
		ClientResource cr = null;
		boolean success = true;
		try {
			cr = new ClientResource(uri);
			cr.setEntityBuffering(true);		// Needed for using RESTlet from JUnit tests
			cr.put(c);
			
			
		} catch (ResourceException re) {
			System.err.println("Error cuando actualizas el circuito: " + cr.getResponse().getStatus());
			success = false;
		}
		
		return success;
	}
	
	
	public boolean deleteCircuito(String circuitoId) {
		ClientResource cr = null;
		boolean success = true;
		try {
			cr = new ClientResource(uri + "/" + circuitoId);
			cr.setEntityBuffering(true);		// Needed for using RESTlet from JUnit tests
			cr.delete();
			
		} catch (ResourceException re) {
			System.err.println("Error cuando borras un circuito " + cr.getResponse().getStatus());
			success = false;
		}
		
		return success;
	}
	
	public boolean addPiloto(String circuitoId, String pilotoId) {
		boolean result = false;
		ClientResource cr = null;
		try {
			cr = new ClientResource(uri+"/"+circuitoId+"/"+pilotoId);
			cr.post(" ");
			result=true;
		} catch (ResourceException re) {
			System.out.println("Error al añadir piloto");
		}
		
		return result;
		
	}
	
	public boolean removePiloto(String circuitoId, String pilotoId) {
		boolean result = false;
		ClientResource cr = null;
		try {
			cr = new ClientResource(uri+"/"+circuitoId+"/"+pilotoId);
			cr.delete();
			result=true;
		} catch (ResourceException re) {
			System.out.println("Error al borrar el piloto: "+re.getStatus());
		}
		
		return result;
	}
}
