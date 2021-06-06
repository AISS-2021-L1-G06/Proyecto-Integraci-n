package aiss.api.consume;


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
	

	public Piloto getPiloto(String pilotoId) {
		//TODO
		return null;
	}
	

	public Piloto addPiloto(Piloto piloto) {
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
