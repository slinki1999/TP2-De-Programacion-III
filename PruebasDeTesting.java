package ProyectoMapViewer;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PruebasDeTesting {
	
	@Test
	public void test1_registrarLocalidadSinNombreIngresado() {
		Usuario u=new Usuario();
		
		u.registrarLocalidad("das","",0.0,0.0);
//		u.getLocalidades().add("d","e",0,0);
	}
	
}
