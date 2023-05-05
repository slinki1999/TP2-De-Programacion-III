package ProyectoMapViewer;

import org.openstreetmap.gui.jmapviewer.Coordinate;

public class Localidad {
	String _nombre;
	String _provincia;
	Coordinate coordenadas;

	public Localidad(String nombre, String provincia, double latitud, double longitud) {
		_nombre = nombre;
		_provincia = provincia;
		coordenadas = new Coordinate(latitud, longitud);
	}

	public String getNombre() {
		return _nombre;
	}

	public void setNombre(String nombre) {
		this._nombre = nombre;
	}

	public Coordinate getCoordenadas() {
		return coordenadas;
	}

	public void setCoordenadas(Coordinate coordenadas) {
		this.coordenadas = coordenadas;
	}

	public String getProvincia() {
		return _provincia;
	}

	public void setProvincia(String provincia) {
		this._provincia = provincia;
	}

	public double getLatitud() {
		return coordenadas.getLat();
	}

	public void setLatitud(double latitud) {
		this.coordenadas.setLat(latitud);
	}

	public double getLongitud() {
		return coordenadas.getLon();
	}

	public void setLongitud(double longitud) {
		this.coordenadas.setLon(longitud);
	}
}
