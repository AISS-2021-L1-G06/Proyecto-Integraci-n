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
			System.err.println("Error when retrieving the playlist: " + cr.getResponse().getStatus());
		}
		
		return list;

	}
	

	public Circuito addCircuito(Circuito c) {
		
		ClientResource cr = null;
		Circuito resultPlaylist = null;
		try {
			cr = new ClientResource(uri);
			cr.setEntityBuffering(true);		// Needed for using RESTlet from JUnit tests
			resultPlaylist = cr.post(c,Circuito.class);
			
		} catch (ResourceException re) {
			System.err.println("Error when adding the playlist: " + cr.getResponse().getStatus());
		}
		
		return resultPlaylist;
	}
	

	public boolean updateCircuito(Circuito c) {
		ClientResource cr = null;
		boolean success = true;
		try {
			cr = new ClientResource(uri);
			cr.setEntityBuffering(true);		// Needed for using RESTlet from JUnit tests
			cr.put(c);
			
			
		} catch (ResourceException re) {
			System.err.println("Error when updating the playlist: " + cr.getResponse().getStatus());
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
			System.err.println("Error when deleting the playlist: " + cr.getResponse().getStatus());
			success = false;
		}
		
		return success;
	}
	
	public boolean addPiloto(String circuitoId, String pilotoId) {
		// TODO
		// Use	cr.post(" ") to avoid RESTlet crashing
		return false;
		
	}
	
	public boolean removePiloto(String circuitoId, String pilotoId) {
		// TODO
		return false;
	}
}
