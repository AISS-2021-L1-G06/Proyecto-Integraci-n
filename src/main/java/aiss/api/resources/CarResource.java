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

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriBuilder;


import javassist.NotFoundException;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;



@Path("/songs")
public class CarResource {

	public static CarResource _instance=null;
	PlaylistRepository repository;
	
	private CarResource(){
		repository=MapPlaylistRepository.getInstance();
	}
	
	public static CarResource getInstance()
	{
		if(_instance==null)
			_instance=new SongResource();
		return _instance; 
	}
	
	@GET
	@Produces("application/json")
	public Collection<Car> getAll()
	{
		return repository.getAllSongs();
	}
	
	
	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Car get(@PathParam("id") String songId) throws NotFoundException
	{
		Car s = repository.getSong(songId);
		
		if(s == null) {
			throw new NotFoundException("The song with id="+ songId +" was not found");
		}
		
		return s;
	}
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response addSong(@Context UriInfo uriInfo, Song song) {
		
		if(song.getTitle()==null || "".equals(song.getTitle())) {
			throw new BadRequestException("El nombre de la cancion no puede ser null");
		}
		
		repository.addSong(song);
		
		//Builds the response. Returns the song that has been added.
		ResponseBuilder resp = null;
		try {
			resp = Response.created(new URI("/songs/"+song.getId()));
		}catch(URISyntaxException e){
			e.printStackTrace();
		}
		resp.entity(song);
		return resp.build();
		
	}
	
	
	@PUT
	@Consumes("application/json")
	public Response updateSong(Song song) throws NotFoundException {
		
		Song oldSong = repository.getSong(song.getId());
		
		if(oldSong==null) {
			throw new NotFoundException("The song with id="+song.getId()+" was not found");
		}
		if(song.getTitle()!=null) {
			oldSong.setTitle(song.getTitle());
		}
		if(song.getAlbum()!=null) {
			oldSong.setAlbum(song.getAlbum());
		}
		if(song.getArtist()!=null) {
			oldSong.setArtist(song.getArtist());
		}
		if(song.getYear()!=null) {
			oldSong.setYear(song.getYear());
		}
		if(song.getId()!=null) {
			oldSong.setId(song.getId());
		}
		
		return Response.noContent().build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response removeSong(@PathParam("id") String songId) throws NotFoundException {
		
		Song toberemoved=repository.getSong(songId);
		if(toberemoved == null) {
			throw new NotFoundException("The song with id="+songId+" was not found");
		}
		else {
			repository.deleteSong(songId);
		}
		return Response.noContent().build();
	}
	
}
