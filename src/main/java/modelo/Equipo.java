package modelo;

public class Equipo {

	private String idEquipo;

	private String nombre;

	private int anoFundacion;

	private int estrellas;

	public Equipo(String idEquipo, String nombre, int anoFundacion, int estrellas) {
		super();
		this.idEquipo = idEquipo;
		this.nombre = nombre;
		this.anoFundacion = anoFundacion;
		this.estrellas = estrellas;
	}

	public Equipo(String nombre, int anoFundacion, int estrellas) {
		super();
		this.nombre = nombre;
		this.anoFundacion = anoFundacion;
		this.estrellas = estrellas;
	}

	public String getIdEquipo() {
		return idEquipo;
	}

	public void setIdEquipo(String idEquipo) {
		this.idEquipo = idEquipo;
	}

	public Equipo() {
		super();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getAnoFundacion() {
		return anoFundacion;
	}

	public void setAnoFundacion(int anoFundacion) {
		this.anoFundacion = anoFundacion;
	}

	public int getEstrellas() {
		return estrellas;
	}

	public void setEstrellas(int estrellas) {
		this.estrellas = estrellas;
	}

}
