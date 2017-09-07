package modelo;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Properties;

import control.Main;

public class EquipoService {

	/**
	 * Lista de equipos
	 */
	private HashMap<String, Equipo> equipos;

	/**
	 * Constructor de la clase
	 * @throws IOException 
	 */
	public EquipoService() throws IOException {
		super();
		equipos = new HashMap<String, Equipo>();
		initEquipos();
	}

	/**
	 * <b>Nombre:</b> getEquipos<br>
	 * <b>Descripción:</b> Retorna la lista de Equipos.<br>
	 * <b>Pre:</b> equipos !=null.
	 * 
	 * @return equipos - Collection(Equipo) - lista equipos.
	 */
	public Collection<Equipo> getEquipos() {
		return equipos.values();
	}

	/**
	 * <b>Nombre:</b> getEquipo<br>
	 * <b>Descripción:</b> Retorna equipo especificado por su id.<br>
	 * 
	 * @param id
	 *            - String. id !=null
	 * @return equipo - Equipo con id dado. Si no encuentra id, retorna null
	 */
	public Equipo getEquipo(String id) {
		return equipos.get(id);
	}

	/**
	 * <b>Nombre:</b> createEquipo<br>
	 * <b>Descripción:</b> Crea y agrega a la lista el nuevo equipo.<br>
	 * 
	 * @param nombre
	 *            - String. Nombre del equipo !=null
	 * @param fundacion
	 *            - int. Ano fundacion equipo. fundacion >= 0
	 * @param estrellas
	 *            - int. Estrellas del equipo. estrellas >= 0
	 * @return equipo - Equipo. equipo creado
	 */
	public Equipo createEquipo(String nombre, int fundacion, int estrellas) {
		String id = "LC" + equipos.size();
		Equipo equipo = new Equipo(id, nombre, fundacion, estrellas);
		return equipos.put(id, equipo);
	}

	/**
	 * <b>Nombre:</b> updateEquipo<br>
	 * <b>Descripción:</b> Edita la información del equipo especificado por su
	 * id.<br>
	 * 
	 * @param id
	 *            -String. id =! null
	 * @param nombre
	 *            - String. Nombre del equipo !=null
	 * @param fundacion
	 *            - int. Ano fundacion equipo. fundacion >= 0
	 * @param estrellas
	 *            - int. Estrellas del equipo. estrellas >= 0
	 * @return equipo - Equipo. Equipo editado. Si no encuentra id, retorna null
	 */
	public Equipo updateEquipo(String id, String nombre, int fundacion, int estrellas) {
		Equipo equipo = equipos.get(id);
		if (equipo != null) {
			equipo.setNombre(nombre);
			equipo.setAnoFundacion(fundacion);
			equipo.setEstrellas(estrellas);
		}
		return equipo;
	}

	/**
	 * <b>Nombre:</b> deleteEquipo<br>
	 * <b>Descripción:</b> Elimina equipo especificado por su id.<br>
	 * 
	 * @param id
	 *            - String. id !=null
	 * @return equipo - Equipo. Equipo eliminado con id dado. Si no encuentra
	 *         id, retorna null
	 */
	public Equipo deleteEquipo(String id) {
		return equipos.remove(id);
	}

	private void initEquipos() throws IOException {
		Properties prop = new Properties();

		InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("txt/equipos.properties");

		prop.load(inputStream);

		int totalEquipos = Integer.parseInt(prop.getProperty("totalEquipos"));

		equipos = new HashMap<String, Equipo>();

		for (int i = 0; i < totalEquipos; i++) {
			String property = prop.getProperty("equipo" + i);
			String[] datos = property.split(";");
			Equipo equipo = new Equipo(datos[0], datos[1], Integer.parseInt(datos[2]), Integer.parseInt(datos[3]));
			equipos.put(datos[0], equipo);
		}

	}

}
