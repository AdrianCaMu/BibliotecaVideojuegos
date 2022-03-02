package pruebas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import Biblioteca.Biblioteca;
import models.VideoJuegos;
import views.BibliotecaView;
import views.CrearVideojuegoView;

class GestionVideojuegosTest {

	@BeforeAll
	public static void before() {
		System.out.println("-------------------- Inicio Prueba Biblioteca --------------------");
		System.out.println();
	}
	
	@AfterAll
	public static void after() {
		System.out.println("-------------------- Fin Prueba Biblioteca --------------------");
	}
	
	@Test
	@Order(1)
	/**
	 * comprobamos que se pueden introducir juegos nuevos a la biblioteca
	 * comprobamos que no se puede añadir un juego que ya está en la biblioteca
	 */
	void CrearVideojuegosTest() {
		
		System.out.println("----- Prueba insertar -----");
		
		CrearVideojuegoView creador = new CrearVideojuegoView();
		
		assertTrue(creador.insertarJuego("prueba", 123, "genero", "desarrolladora", 100));
		
		System.out.println("Juego insertado");
		
		assertTrue(creador.insertarJuego("prueba2", 123, "genero", "desarrolladora", 100));
		
		System.out.println("Juego insertado");

		assertFalse(creador.insertarJuego("prueba", 123, "genero", "desarrolladora", 100));
		
		System.out.println("Juego repetido, no insertado");

	}
	
	@Test
	@Order(2)
	/**
	 * comprobamos que podemos eliminar un juego de la biblioteca
	 */
	void BorrarVideojuegosTest() {
		
		System.out.println("----- Prueba borrar -----");
		
		Biblioteca.juegos.clear();
		
		Biblioteca.juegos.add(new VideoJuegos("Los Simpson", 3, "Aventuras", "Bart Company", 2));
		
		System.out.println("Juego insertado");
		
		BibliotecaView biblio = new BibliotecaView();
		biblio.eliminar();
		//hacemos click en confirmnar que queremos borrar
		
		assertEquals(Biblioteca.juegos.size(), 0);
		
		System.out.println("Juego eliminado");

	}
	
	@Test
	@Order(3)
	/**
	 * comprobamos que se puede modificar un juego de la biblioteca
	 */
	void ModificarVideojuegosTest() {
		
		System.out.println("----- Prueba Modificar -----");
		
		Biblioteca.juegos.clear();
		
		Biblioteca.juegos.add(new VideoJuegos("Los Simpson", 3, "Aventuras", "Bart Company", 2));
		
		System.out.println("Juego insertado");
		
		BibliotecaView biblio = new BibliotecaView();
		
		biblio.updateJuego("Cambiado", "Aventuras", "Bart Company", 2, 2);
		
		assertEquals(Biblioteca.juegos.get(0).getNombre(), "Cambiado");
		
		System.out.println("Juego cambiado");

	}
}
