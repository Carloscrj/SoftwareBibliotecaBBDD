package dam.main;

import java.util.ArrayList;
import java.util.Scanner;

import dam.db.AccesoDB;
import dam.pojo.Autor;
import dam.pojo.Libro;


public class GestionBiblioteca {
	
	static AccesoDB accesoDB;
	static Scanner sc;
	static String accionIL= "IL";
	static String accionML= "ML";
	static String accionCT= "CT";
	static String accionCL= "CL";
	static String accionS= "S";
	
	public static void main(String[] args) {
		accesoDB = new AccesoDB();
		sc = new Scanner(System.in);
		
		opcionElegida();
		
		sc.close();
		
		accesoDB.cerraDB();
	}

	private static void opcionElegida() {
		boolean continuar=true;
		String accion;
		
		while (continuar) {
			
			accion=solicitarAccion();
			
			if(accion.equalsIgnoreCase(accionIL)) {
				insertarAutoresYlibros();
			}else if(accion.equalsIgnoreCase(accionML)) {
				modificarNumeroDePaginas();
			}else if (accion.equalsIgnoreCase(accionCT)) {
				consultarTodosLibros();
			}else if (accion.equalsIgnoreCase(accionCL)){
				consultarLibrosMasDe300Paginas();
			}else{
				System.out.println("Fin del proceso.");
				continuar=false;
			}
		
		}
		
	}

	private static void consultarLibrosMasDe300Paginas() {
		ArrayList<Libro> listaLibros = accesoDB.mostrarLibrosMasDe300Paginas();
		
		if (listaLibros.isEmpty()) {
			System.out.println("No se han encontrado libros con más de 300 páginas.");
		}else {
			System.out.println("Se han encontrado "+listaLibros.size()+ " libro/s con más de 300 páginas.");
			for (Libro libro : listaLibros) {
				System.out.println(libro);
			}
		}
		
	}

	private static void consultarTodosLibros() {
		ArrayList<Libro> listaLibros = accesoDB.consultarTodos();
		if (listaLibros.isEmpty()) {
			System.out.println("\nNo se han encontrado datos en la biblioteca.");
		}else {
			System.out.println("\nSe han encontrado "+listaLibros.size()+ " libros.");
			for (Libro libro : listaLibros) {
				System.out.println(libro);
			}
		}
		
	}

	private static void modificarNumeroDePaginas() {
		System.out.println("Introduzca el título del libro que desea modificar");
		String titulo= sc.nextLine();
		System.out.println("Introduzca el nuevo número de páginas del libro.");
		int numeroPaginasNuevo = Integer.parseInt(sc.nextLine());
		
		int paginas = accesoDB.modificaPaginas(titulo, numeroPaginasNuevo);
	}

	private static void insertarAutoresYlibros() {
		
		boolean autorEncontrado = true;
		String nombre = null;
		Autor autor = null;
	
			
		nombre = validarString("Introduzca nombre del autor");
		autorEncontrado = accesoDB.buscarAutor(nombre);
			if(autorEncontrado){
				autor = accesoDB.recuperarAutorNombre(nombre);
				
				String titulo = validarString("Introduzca título del libro");
				int numeroDePaginas = validarInteger("Introduzca número de páginas de un libro");
				
				
				Libro libro = new Libro(titulo, autor, numeroDePaginas);
				
				Libro nuevoLibro =  accesoDB.insertarAutorYLibro(libro);
				
			} else {
				String iniciales = validarString("Introduzca iniciales del autor");
				String nacionalidad = validarString("Introduzca nacionalidad del autor");
				
				
				String titulo = validarString("Introduzca título del libro");
				int numeroDePaginas = validarInteger("Introduzca número de páginas de un libro");
				
				
				Libro libro = new Libro(titulo, new Autor(iniciales, nombre, nacionalidad), numeroDePaginas);
				
				Libro nuevoLibro =  accesoDB.insertarAutorYLibro(libro);
			}
			
	}

	private static int validarInteger(String msg) {
		boolean intNoValido = true;
		int dato = 0;
		
		while(intNoValido) {
			System.out.println(msg);
			dato = Integer.parseInt(sc.nextLine());
				if(dato<=0){
					System.out.println("Opción no valida.");
				} else {
					intNoValido =  false;
				}
				
		}
		return dato;
	}

	private static String validarString(String msg) {
		
		boolean stringNoValido = true;
		String dato = null;
		
		while(stringNoValido) {
			System.out.println(msg);
			dato = sc.nextLine();
				if(dato.isBlank()){
					System.out.println("Opción no valida campo en blanco.");
				} else {
					stringNoValido =  false;
				}
				
		}
		return dato;
	}

	private static String solicitarAccion() {
		String mensaje=("Que acción desea realizar"+"\n - Introducir IL para insertar autores y libros. "+
				"\n - Introducir ML para modificar el número de páginas de un libro."+"\n - Introducir CT para consultar todos los libros."+
				"\n - Introducir CL para consultar los libros con más de 300 páginas.")+"\n - Introducir S para terminar el proceso.  ";
		
		String opcion= controlOpciones(mensaje,"IL","ML","CT","CL","S");

		return opcion;
	}

	private static String controlOpciones(String mensaje, String il, String ml, String ct, String cl,
			String s) {
		
		boolean valorNoval = true;
		String opcion = null;
		
		while(valorNoval) {
				System.out.println(mensaje);
				opcion= sc.nextLine();
				
				if(opcion.equalsIgnoreCase(il)||opcion.equalsIgnoreCase(ml)||opcion.equalsIgnoreCase(ct)||
						opcion.equalsIgnoreCase(cl)||opcion.equalsIgnoreCase(s)) {
					valorNoval = false;
				} else {
					System.out.println("Opción no valida las opciones a introducir son "+il+", "+ml+", "+ct+", "+cl+" o "+s+".");
				}
				
		}
		
		return opcion;
		
	}

}
