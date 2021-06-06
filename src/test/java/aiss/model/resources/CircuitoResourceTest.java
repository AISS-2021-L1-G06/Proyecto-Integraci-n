package aiss.model.resources;

import static org.junit.Assert.*;

import java.util.Collection;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.restlet.resource.ResourceException;

import aiss.api.consume.CircuitoResource;
import aiss.api.consume.PilotoResource;
import aiss.api.consume.Piloto;
import aiss.api.consume.Circuito;

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
		
	
		piloto = sr.addPiloto(new Piloto("Test escuderia","Test nombre",65,5, 4));
		if(piloto!=null)
			plr.addPiloto(circuito.getId(), piloto.getId());
	}

	@AfterClass
	public static void tearDown() throws Exception {
		plr.deleteCircuito(circuito.getId());
		plr.deleteCircuito(circuito3.getId());
		plr.deleteCircuito(circuito4.getId());
		if(piloto!=null)
			sr.deletePiloto(piloto.getId());
	}

	@Test
	public void testGetAll() {
		Collection<Circuito> circuitos = plr.getAll(); 
		
		assertNotNull("The collection of circuitos is null", circuitos);
		
		// Show result
		System.out.println("Listing all circuitos:");
		int i=1;
		for (Circuito pl : circuitos) {
			System.out.println("Circuito " + i++ + " : " + pl.getNombre() + " (ID=" + pl.getId() + ")");
		}
		
	}

	@Test
	public void testGetCircuito() {
		Circuito p = plr.getCircuito("c5");
		
		
		// Show result
		System.out.println("Circuito id: " +  p.getId());
		System.out.println("Circuito name: " +  p.getNombre());

	}

	@Test
	public void testAddCircuito() {
		String circuitoName = "Añadir nombre del circuito";
		String circuitoCanalEmision = "Añadir canal de emisión";
		
		circuito4 = plr.addCircuito(new Circuito(circuitoName,circuitoCanalEmision));
		
		assertNotNull("Error añadiendo el circuito", circuito4);
		assertEquals("El nombre del circuito no se ha establecido correctamente", circuitoName, circuito4.getNombre());
		assertEquals("The circuito's description has not been setted correctly", circuitoCanalEmision, circuito4.getCanalEmision());
	}

	@Test
	public void testUpdateCircuito() {
		String circuitoName = "Nombre del cicuito actualizado";

		// Update circuito
		circuito.setNombre(circuitoName);

		boolean success = plr.updateCircuito(circuito);
		
		assertTrue("Error actualizando el circuito", success);
		
		Circuito pl  = plr.getCircuito(circuito.getId());
		assertEquals("El nombre del circuito no se ha actualizado correctamente", circuitoName, pl.getNombre());

	}

	@Test
	public void testDeleteCircuito() {
		boolean success = plr.deleteCircuito(circuito2.getId());
		assertTrue("Error eliminando el circuito", success);
		
		Circuito pl = plr.getCircuito(circuito2.getId());
		assertNull("El circuito no se ha eliminado correctamente", pl);
	}

	@Test
	public void testAddPiloto() {
		if(piloto!=null) {
			boolean success = plr.addPiloto(circuito3.getId(), piloto.getId());
			assertTrue("Error añadiendo el piloto", success);
		}
	}

	@Test
	public void testRemovePiloto() {
		boolean result = plr.removePiloto(circuito.getId(), piloto.getId());
		assertTrue(result);
	}

}
