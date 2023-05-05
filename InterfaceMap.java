package ProyectoMapViewer;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;
import org.openstreetmap.gui.jmapviewer.interfaces.MapPolygon;

import Pruebas.Interfaz;
import javax.swing.JTextField;
import java.awt.BorderLayout;

public class InterfaceMap {

	private JFrame frame;
	private JMapViewer mapa;
	private MapPolygon polygon;
	private JTextField t;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceMap window = new InterfaceMap();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InterfaceMap() {
		crearLaInterfazGrafica();
		initialize();

	}

	private void crearLaInterfazGrafica() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("JMapViewer");
		frame.getContentPane().setLayout(null);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Usuario usuario = new Usuario();
		List<MapMarkerDot> verticesGraficados = new ArrayList<MapMarkerDot>();
		ArrayList<Coordinate> c = new ArrayList<Coordinate>();
		
		frame.setBounds(100, 100, 800, 700);

		mapa = new JMapViewer();
		mapa.setZoomControlsVisible(true);
		mapa.setBounds(100, 100, 500, 500);
		mapa.setBackground(Color.white);
		frame.getContentPane().add(mapa);

		Coordinate coordinate = new Coordinate(-34.521, -58.719);
		mapa.setDisplayPosition(coordinate, 12);

//		Scanner input=new Scanner(System.in);
		mapa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Coordinate p = (Coordinate) mapa.getPosition(e.getPoint());
				
				if (e.getButton() == MouseEvent.BUTTON1) {
					System.out.println(p);
					try {
						String nombre = JOptionPane.showInputDialog("Nombre: ");
						String provincia = JOptionPane.showInputDialog("Provincia: ");
						if (!nombre.equals(null) && !nombre.isEmpty() && !provincia.isEmpty()
								&& !provincia.equals(null)) {
							BigDecimal a=new BigDecimal(p.getLat());
							BigDecimal b= new BigDecimal(p.getLon());
							
							BigDecimal auxLatitud=a.setScale(4,BigDecimal.ROUND_DOWN);
							BigDecimal auxLongitud=b.setScale(4,BigDecimal.ROUND_DOWN);
							
							double valorLongitudTruncado = Double.parseDouble(auxLongitud.toString());
							double valorLatitudTruncado = Double.parseDouble(auxLatitud.toString());
							usuario.registrarLocalidad(nombre, provincia, valorLatitudTruncado , valorLongitudTruncado);
							MapMarkerDot crearVertice = new MapMarkerDot(p);
							System.out.println(valorLatitudTruncado + " -- " + valorLongitudTruncado);
							System.out.println(auxLatitud.negate());
							String s = "Nombre: " + nombre + " ; Provincia: " + provincia + " ; Latitud: " + auxLatitud
									+ " ;Longitud: " + auxLongitud + "\n";
							usuario.saveDate(s);
							usuario.writeScoreFile("localidadesregistradas.txt");
							
							c.add(new Coordinate (valorLatitudTruncado,valorLongitudTruncado));
							verticesGraficados.add(crearVertice);
							mapa.addMapMarker(crearVertice);

						}
					} catch (Exception error) {
						System.out.println("No se ingreso el nombre de la localidad o la Provincia.");
					}

				}
			}
		});


		JButton construirGrafo = new JButton("Generar Grafo");
		construirGrafo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				polygon = new MapPolygonImpl(c);
				mapa.addMapPolygon(polygon);
			}
		});
		construirGrafo.setBounds(634, 48, 119, 23);
		frame.getContentPane().add(construirGrafo);

		JButton eliminarGrafo = new JButton("Eliminar Grafo");
		eliminarGrafo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mapa.removeAllMapPolygons();

			}
		});
		eliminarGrafo.setBounds(634, 88, 119, 23);
		frame.getContentPane().add(eliminarGrafo);

		t = new JTextField();
		t.setBounds(634, 168, 119, 23);
		frame.getContentPane().add(t);
		t.setColumns(10);

		JButton eliminarVertice = new JButton("Eliminar Vertice");
		eliminarVertice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < verticesGraficados.size(); i++) {
					if (t.getText().equals(usuario.getLocalidades().get(i).getNombre())
							&& usuario.getLocalidades().get(i).getLatitud() == c.get(i).getLat()
							&& usuario.getLocalidades().get(i).getLongitud() == c.get(i).getLon()) {

						try {
							usuario.getLocalidades().remove(i);
							c.remove(i);
							mapa.removeMapMarker(verticesGraficados.get(i));
							verticesGraficados.remove(i);
						} catch (Exception e2) {
							System.out.println("Error.");
						}
					}
				}
			}
		});
		eliminarVertice.setBounds(634, 128, 119, 23);
		frame.getContentPane().add(eliminarVertice);

	}

}
