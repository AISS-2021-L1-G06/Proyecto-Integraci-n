package aiss.model.resources;

import static org.junit.Assert.*;

import java.util.Collection;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.restlet.resource.ResourceException;

import aiss.model.Circuito;
import aiss.model.Piloto;

public class CircuitoResourceTest {

	static Circuito circuito, circuito2, circuito3, circuito4;
	static Piloto piloto;
	static CircuitoResource plr = new CircuitoResource();
	static PilotoResource sr = new PilotoResource();
	
	@BeforeClass
	public static void setUp() throws Exception {
		
		circuito = plr.addCircuito(new Circuito("Test list 1"));
		circuito2 = plr.addCircuito(new Circuito("Test list 2"));
		circuito3 = plr.addCircuito(new Circuito("Test list 3"));
		
	
		piloto = sr.addPiloto(new Piloto("Test title","Test artist","Test album","2016"));
		if(piloto!=null)
			plr.addPiloto(circuito.getId(), piloto.getId());
	}

	@AfterClass
	public static void tearDown() throws Exception {
		plr.deleteCircuito(circuito.getId());
		plr.deleteCircuito(circuito3.getId());
		plr.deleteCircuito(circuito4.getId());
		if(piloto!=null)
			sr.deletePiloto(song.getId());
	}

	@Test
	public void testGetAll() {
		Collection<Circuito> circuitos = plr.getAll(); 
		
		assertNotNull("The collection of circuitos is null", circuitos);
		
		// Show result
		System.out.println("Listing all circuitos:");
		int i=1;
		for (Circuito pl : circuitos) {
			System.out.println("Circuito " + i++ + " : " + pl.getName() + " (ID=" + pl.getId() + ")");
		}
		
	}

	@Test
	public void testGetCircuito() {
		Circuito p = plr.getCircuito(circuito.getId());
		
		assertEquals("The id of the circuitos do not match", circuito.getId(), p.getId());
		assertEquals("The name of the circuitos do not match", circuito.getName(), p.getName());
		
		// Show result
		System.out.println("Circuito id: " +  p.getId());
		System.out.println("Circuito name: " +  p.getName());

	}

	@Test
	public void testAddCircuito() {
		String circuitoName = "Add circuito test title";
		String circuitoDescription = "Add circuito test description";
		
		circuito4 = plr.addCircuito(new Circuito(circuitoName,circuitoDescription));
		
		assertNotNull("Error when adding the circuito", circuito4);
		assertEquals("The circuito's name has not been setted correctly", circuitoName, circuito4.getName());
		assertEquals("The circuito's description has not been setted correctly", circuitoDescription, circuito4.getDescription());
	}

	@Test
	public void testUpdateCircuito() {
		String circuitoName = "Updated circuito name";

		// Update circuito
		circuito.setName(circuitoName);

		boolean success = plr.updateCircuito(circuito);
		
		assertTrue("Error when updating the circuito", success);
		
		Circuito pl  = plr.getCircuito(circuito.getId());
		assertEquals("The circuito's name has not been updated correctly", circuitoName, pl.getName());

	}

	@Test
	public void testDeleteCircuito() {
		boolean success = plr.deleteCircuito(circuito2.getId());
		assertTrue("Error when deleting the circuito", success);
		
		Circuito pl = plr.getCircuito(circuito2.getId());
		assertNull("The circuito has not been deleted correctly", pl);
	}

	@Test
	public void testAddPiloto() {
		if(piloto!=null) {
			boolean success = plr.addPiloto(circuito3.getId(), piloto.getId());
			assertTrue("Error when adding the piloto", success);
		}
	}

	@Test
	public void testRemovePiloto() {
		boolean result = plr.removePiloto(circuito.getId(), piloto.getId());
		assertTrue(result);
	}

}
