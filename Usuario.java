package ProyectoMapViewer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.openstreetmap.gui.jmapviewer.Coordinate;

public class Usuario {
	public double costoTotal;
	private List<Localidad> localidades;
	private List<String> guardarRegistros;

	Usuario() {
		costoTotal=0;
		localidades = new ArrayList<Localidad>();
		guardarRegistros = new ArrayList<String>();
	}
	public double distanciaEntreDosLocalidades(Localidad l1, Localidad l2) {
		Coordinate latL1=new Coordinate(l1.getLatitud(),l1.getLongitud());
		Coordinate latL2=new Coordinate(l2.getLatitud(),l2.getLongitud());
		if () {
			
		}
		return s;
	}

	public void registrarLocalidad(String nombre, String provincia, double latitud, double longitud) {
		if (!nombre.equals("")) {
			localidades.add(new Localidad(nombre, provincia, latitud, longitud));
		}
		
	}

	public double getCostoTotal() {
		return costoTotal;
	}

	public void setCostoTotal(double costo) {
		this.costoTotal = costo;
	}

	public List<Localidad> getLocalidades() {
		return localidades;
	}

	public List<String> getGuardarRegistros() {
		return guardarRegistros;
	}

	// Guardar registros
	protected void saveDate(String dato) {
		guardarRegistros.add(dato);
	}

	// Leer Documento
	protected List<String> readScoreFile(String pathScoreFile) {
		List<String> registros = new ArrayList<String>();
		BufferedReader lector;
		FileReader archivo;
		File f;

		try {
			f = new File(pathScoreFile);
			archivo = new FileReader(f);
			if (archivo.ready()) {
				lector = new BufferedReader(archivo);
				String cadena;

				while ((cadena = lector.readLine()) != null) {
					registros.add(cadena);

				}
				archivo.close();
				return registros;
			} else {
				System.out.println("El archivo no esta listo para ser leido...");
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		return registros;
	}

	protected void writeScoreFile(String pathScoreFile) {
		FileWriter file = null;

		try {
			file = new FileWriter(pathScoreFile);

			for (String t : guardarRegistros) {
				file.write(t);
			}
			file.close();

		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

}
