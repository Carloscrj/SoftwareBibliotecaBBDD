package dam.db;

import java.util.ArrayList;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Constraint;
import com.db4o.query.Query;

import dam.pojo.Autor;
import dam.pojo.Libro;




public class AccesoDB {
	static final String PATH_DB = "DB4O/biblioteca.yap"; 
	ObjectContainer db;
	
	public AccesoDB() {
		 db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), PATH_DB);

	}
	
	public void cerraDB() {
		db.close();
	}
	
	/*public void insertar(Libro libro) {
		db.store(libro);
	}*/

	public Libro insertarAutorYLibro(Libro libro) {
		db.store(libro);
		
		return libro;
	}

	public int modificaPaginas(String titulo, int numeroPaginasNuevo) {
		Libro libroModif = new Libro();
		libroModif.setTitulo(titulo);
		
		ObjectSet<Libro> setLibro = db.queryByExample(libroModif);
		int cantidad = 0;
		
		while (setLibro.hasNext()) {  
			libroModif = setLibro.next();
			libroModif.setNumeroDePaginas(numeroPaginasNuevo);
			
			db.store(libroModif);
			cantidad++;
		}
		return cantidad;
		
	}

	public ArrayList<Libro> consultarTodos() {
		
		 ArrayList<Libro> listaLibros = new ArrayList<Libro>();
		 
		 ObjectSet<Libro> setLibros = db.queryByExample(Libro.class); //
		 
		 listaLibros.addAll(setLibros);
		 
		 return listaLibros;
	}

	public ArrayList<Libro> mostrarLibrosMasDe300Paginas() {
		
		ArrayList<Libro> listaLibros = new ArrayList<Libro>(); 
		ObjectSet<Libro> setLibros = db.queryByExample(Libro.class);
		
		for (Libro libroBusqueda : setLibros) {
			if (libroBusqueda.getNumeroDePaginas()>=300) {
				listaLibros.add(libroBusqueda);
				
			}
		}
		
		return listaLibros;
		
	}

	public boolean buscarAutor(String nombre) {
		boolean encontrado = false;
		
		Query consulta =db.query();
		consulta.constrain(Autor.class);
		
		//a continuacion le decimos que tenga en cuenta nombre
		consulta.descend("nombre").constrain(nombre);
		
		ObjectSet<Autor> setNombreAutor = consulta.execute();
		
		if (setNombreAutor.isEmpty()) {
			
		}else {
			encontrado = true;
		}
		
		return encontrado;
	}

	public Autor recuperarAutorNombre(String nombre) {
		Autor autorNombre = new Autor();
		autorNombre.setNombre(nombre);
		
		ObjectSet<Autor> setAutores = db.queryByExample(autorNombre);
		
		if (setAutores.hasNext()) {
			autorNombre = setAutores.next();
		}
		return autorNombre;
	}

	
}
