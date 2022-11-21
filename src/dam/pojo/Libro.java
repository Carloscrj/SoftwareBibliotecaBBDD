package dam.pojo;

public class Libro {
	public static final String ATR_TITULO = "título";
	public static final String ATR_AUTOR = "autor";
	public static final String ATR_NUMPAGINAS = "numeroDePaginas";
	
	private String titulo;
	private Autor autor;
	private int numeroDePaginas;
	
	public Libro(String titulo, Autor autor, int numeroDePaginas) {
		this.titulo = titulo;
		this.autor = autor;
		this.numeroDePaginas = numeroDePaginas;
	}
	
	public Libro() {
		
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public int getNumeroDePaginas() {
		return numeroDePaginas;
	}

	public void setNumeroDePaginas(int numeroDePaginas) {
		this.numeroDePaginas = numeroDePaginas;
	}

	public static String getAtrTitulo() {
		return ATR_TITULO;
	}

	public static String getAtrAutor() {
		return ATR_AUTOR;
	}

	public static String getAtrNumpaginas() {
		return ATR_NUMPAGINAS;
	}

	@Override
	public String toString() {
		return "Libro [titulo=" + titulo + ", autor=" + autor + ", numeroDePaginas=" + numeroDePaginas + "]";
	}

	
	
	
	
}
