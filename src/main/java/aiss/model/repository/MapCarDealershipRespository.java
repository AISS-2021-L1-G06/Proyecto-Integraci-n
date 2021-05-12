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
		return playlistMap.get(id);
	}
	
	@Override
	public void updatePlaylist(Playlist p) {
		playlistMap.put(p.getId(),p);
	}

	@Override
	public void deletePlaylist(String id) {	
		playlistMap.remove(id);
	}
	

	@Override
	public void addSong(String playlistId, String songId) {
		Playlist playlist = getPlaylist(playlistId);
		playlist.addSong(songMap.get(songId));
	}

	@Override
	public Collection<Song> getAll(String playlistId) {
		return getPlaylist(playlistId).getSongs();
	}

	@Override
	public void removeSong(String playlistId, String songId) {
		getPlaylist(playlistId).deleteSong(songId);
	}

	
	// Song related operations
	
	@Override
	public void addSong(Song s) {
		String id = "s" + index++;
		s.setId(id);
		songMap.put(id, s);
	}
	
	@Override
	public Collection<Song> getAllSongs() {
			return songMap.values();
	}

	@Override
	public Song getSong(String songId) {
		return songMap.get(songId);
	}

	@Override
	public void updateSong(Song s) {
		Song song = songMap.get(s.getId());
		song.setTitle(s.getTitle());
		song.setAlbum(s.getAlbum());
		song.setArtist(s.getArtist());
		song.setYear(s.getYear());
	}

	@Override
	public void deleteSong(String songId) {
		songMap.remove(songId);
	}
	
}
