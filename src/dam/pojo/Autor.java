package dam.pojo;

public class Autor {
	String iniciales;
	String nombre;
	String nacionalidad;
	
	public Autor(String iniciales, String nombre, String nacionalidad) {
		this.iniciales = iniciales;
		this.nombre = nombre;
		this.nacionalidad = nacionalidad;
	}
	
	public Autor() {
		
	}

	public String getIniciales() {
		return iniciales;
	}

	public void setIniciales(String iniciales) {
		this.iniciales = iniciales;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	@Override
	public String toString() {
		return "[iniciales=" + iniciales + ", nombre=" + nombre + ", nacionalidad=" + nacionalidad + "]";
	}
	
	
	
	
	
	
}
