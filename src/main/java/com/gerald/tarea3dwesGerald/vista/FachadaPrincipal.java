package com.gerald.tarea3dwesGerald.vista;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gerald.tarea3dwesGerald.modelo.Credenciales;
import com.gerald.tarea3dwesGerald.modelo.Persona;
import com.gerald.tarea3dwesGerald.servicios.ServiciosFactory;
import com.gerald.tarea3dwesGerald.servicios.Sesion;
import com.gerald.tarea3dwesGerald.servicios.Sesion.Perfil;

import jakarta.annotation.PostConstruct;


@Component
public class FachadaPrincipal {
	
	@Autowired
	private ServiciosFactory factory;
	
	@Autowired
	private FachadaPlantas menuPlantas;
	
	Scanner teclado = new Scanner(System.in);

	Sesion s = new Sesion("", Perfil.INVITADO);
	boolean sesionActiva = true;
	
	private static FachadaPrincipal instance;

    @PostConstruct
    public void init() {
        instance = this;
    }

    public static FachadaPrincipal getInstance() {
        return instance;
    }
	
	private void menuInvitado() {
		System.out.println("");
		System.out.println("***********************************************");
		System.out.println("Bienvenido al menu de gestión de un vivero");
		System.out.println("1.  Ver Plantas.");
		System.out.println("2.  Iniciar sesión.");
		System.out.println("0.  Cerrar aplicación.");
		System.out.println("Seleccione una opcion:");
	}

	private void menuAdministrador() {
		System.out.println("");
		System.out.println("***********************************************");
		System.out.println("Bienvenido administrador");
		System.out.println("1.  Registrar persona.");
		System.out.println("2.  Gestionar plantas.");
		System.out.println("3.  Gestionar ejemplares.");
		System.out.println("4.  Gestionar mensajes.");
		System.out.println("5.  Cerrar sesión.");
		System.out.println("0.  Cerrar aplicación.");
		System.out.println("Seleccione una opcion:");
	}


	private void menuUsuarioRegistrado(String usuario) {
		System.out.println("");
		System.out.println("***********************************************");
		System.out.println("Bienvenido/a " + usuario);
		System.out.println("1.  Gestionar ejemplares.");
		System.out.println("2.  Gestionar mensajes.");
		System.out.println("3.  Cerrar sesión.");
		System.out.println("0.  Cerrar aplicación.");
		System.out.println("Seleccione una opcion:");

	}
	
	public void mostrarMenuInvitado() {
		int opcion = -1;
		do {
			this.menuInvitado();
			try {
				opcion = teclado.nextInt();
				teclado.nextLine();
				if (opcion < 0 || opcion > 2) {
					System.err.println("Opción fuera de rango. Inténtelo de nuevo.");
					continue;
				}

				switch (opcion) {
				case 1:
					//Ver lista plantas
					if (factory.getServiciosPlanta().listaPlantas().size() == 0) {
						System.out.println("No hay plantas almacenadas");
					} else {
						System.out.println("Lista de plantas");
						for (int i = 0; i < factory.getServiciosPlanta().listaPlantas().size(); i++) {
							int numero = i + 1;
							System.out.println(numero + "ª " + factory.getServiciosPlanta().listaPlantas().get(i));
						}
					}
					break;
				case 2:
					
					//Login
					System.out.print("Introduce tu usuario: ");
					String usuario = teclado.nextLine();
					System.out.print("Introduce tu contraseña: ");
					String contraseña = teclado.nextLine();
					if (factory.getServiciosCredenciales().login(usuario, contraseña)) {
						s.setUsuario(usuario);
						s.setPerfil(Perfil.REGISTRADO);
						if (usuario.equals("admin")) {
							s.setPerfil(Perfil.ADMIN);
							System.out.println("Conectado");
							this.mostrarMenuAdministrador(s);

						} else {
							//this.mostrarMenuUsuarioRegistrado(s);
							System.out.println("Conectado "+s.getUsuario());
						}
					} else {
						System.out.println("Usuario o contraseña incorrectos");
					}

					break;
				case 0:
					//Cerrar programa
					sesionActiva = false;
					teclado.close();
					break;
				}
			} catch (InputMismatchException e) {
				System.err.println("Entrada inválida. Por favor, ingrese un número entero.");
				teclado.next();
			}
		} while (sesionActiva);

		teclado.close();
	}
	
	public void mostrarMenuAdministrador(Sesion s) {
		int opcion = -1;
		do {
			this.menuAdministrador();
			try {
				opcion = teclado.nextInt();
				teclado.nextLine();
				if (opcion < 0 || opcion > 5) {
					System.err.println("Opción fuera de rango. Inténtelo de nuevo.");
					continue;
				}

				switch (opcion) {
				case 1:
					//Registrar persona
					String nombre = "";
					do {
						System.out.println("Introduce el nombre de la persona: ");
						nombre = teclado.nextLine();

						if (!factory.getComprobaciones().verificarNombrePersona(nombre)) {
							System.out.println(
									"Nombre no válido. Recuerde que como máximo puede contener 25 caracteres y no puede contener númenos, ni carácteres");
						}

					} while (!factory.getComprobaciones().verificarNombrePersona(nombre));

					String email = "";
					do {
						System.out.println("Introduce el email de la persona: ");
						email = teclado.nextLine();

						if (!factory.getComprobaciones().esEmailValido(email)) {
							System.out.println("Formato de email no válido");
						}

						if (factory.getServiciosPersona().existeEmail(email)) {
							System.out.println("El email ya existe");
						}
					} while (factory.getServiciosPersona().existeEmail(email) || !factory.getComprobaciones().esEmailValido(email));

					String usuario = "";

					do {
						System.out.println("Introduce el usuario de la persona: ");
						usuario = teclado.nextLine();

						if (factory.getComprobaciones().comprobarUsuario(usuario)) {
							System.out.println("El usuario no puede contener espacios en blanco");
						}

						if (usuario.equalsIgnoreCase("admin")) {
							System.out.println("Nombre de usuario reservado.");
						}

						if (factory.getServiciosCredenciales().existeUsuario(usuario)) {
							System.out.println("El usuario ya existe");
						}
					} while (factory.getComprobaciones().comprobarUsuario(usuario) || factory.getServiciosCredenciales().existeUsuario(usuario)
							|| usuario.equalsIgnoreCase("admin"));

					String password = "";
					do {
						System.out.println("Introduce la contraseña de la persona: ");
						password = teclado.nextLine();

						if (factory.getComprobaciones().comprobarEspaciosBlanco(password)) {
							System.out.println("La contraseña no puede contener espacios en blanco");
						}

						if (!factory.getComprobaciones().esContrasenaValida(password)) {
							System.out.println(
									"La contraseña no es válida. Recuerda que la contraseña tiene que tener una longitud de 6 carácteres minimo, incluyendo una letra y un número mínimo");
						}
					} while (!factory.getComprobaciones().esContrasenaValida(password)
							|| factory.getComprobaciones().comprobarEspaciosBlanco(password));
					
					Persona p1 = new Persona(nombre, email);
					Credenciales c1 = new Credenciales (usuario,password);

					if (factory.getServiciosPersona().crearUsuario(p1,c1) != null) {
						System.out.println("Usuario registrado correctamente");
					} else {
						System.err.println("No se ha podido registrar el usuario");
					}
					break;
				case 2:
					menuPlantas.mostrarMenuGestionarPlantas();					
					break;
				case 3:
					//Llama al menu de gestionar ejemplares
					break;
				case 4:
					//Llama al menu de gestionar mensajes
					
					break;
				case 5:
					//Cerrar sesión
					System.out.println("Hasta luego " + s.getUsuario());
					s.cerrarSesion();
					this.mostrarMenuInvitado();
					break;
				case 0:
					//Cerrar programa
					sesionActiva = false;
					s.cerrarSesion();
					teclado.close();
					break;
				}
			} catch (InputMismatchException e) {
				System.err.println("Entrada inválida. Por favor, ingrese un número entero.");
				teclado.next();
			}

		} while (sesionActiva);

	}

}
