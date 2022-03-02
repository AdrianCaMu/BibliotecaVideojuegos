package pruebas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import Biblioteca.Biblioteca;
import models.Usuario;
import views.LoginView;
import views.RegistroView;

class GestionUsuariosTest {
	
	@BeforeAll
	public static void before() {
		System.out.println("-------------------- Inicio Prueba Usuarios --------------------");
		System.out.println();
	}
	
	@AfterAll
	public static void after() {
		System.out.println("-------------------- Fin Prueba Usuarios --------------------");
	}
	
	@Test
	@Order(1)
	/**
	 * con este test probamos que un usuario se pueda registrar si pone un usuario que no existe ya
	 * Una contraseña de mas de 8 caracteres
	 * Las dos contraseñas son iguales
	 */
	void RegistroTest() {
		
		System.out.println("----- Prueba registro -----");
		
		RegistroView registro = new RegistroView();
		
		assertTrue(registro.registrar("prueba", "123456789", "123456789"));
		
		System.out.println("Usuario registrado");
		
		assertFalse(registro.registrar("prueba", "123456789", "123456789"));
		
		System.out.println("Usuario repetido, no registrado");

		assertFalse(registro.registrar("prueba2", "123", "123"));
		
		System.out.println("Contraseña menor a 8 caracteres, no registrado");

		assertFalse(registro.registrar("prueba2", "987654321", "123456789"));
		
		System.out.println("Contraseñas no coinciden, no registrado");

	}
	
	@Test
	@Order(2)
	/**
	 * con este test comprobamos que un usuario existente puede iniciar sesion si pone su usuario y contraseña correctamente
	 */
	void Logintest() {
		
		System.out.println("----- Prueba Login -----");
		
		Biblioteca almacen = new Biblioteca();
		almacen.usuarios.add(new Usuario("prueba", "123456789"));
		
		System.out.println("Usuario registrado");
		
		LoginView login = new LoginView();
		
		assertTrue(login.comprobarLogin("prueba", "123456789"));
		
		System.out.println("Login correcto");
		
		assertFalse(login.comprobarLogin("prueba", "987654321"));
		
		System.out.println("Contraseña no coincide, Login fallido");
		
		assertFalse(login.comprobarLogin("diferente", "123456789"));
		
		System.out.println("Usuario no existente, Login fallido");
	}

}
