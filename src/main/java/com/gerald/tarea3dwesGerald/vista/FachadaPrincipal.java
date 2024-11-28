package com.gerald.tarea3dwesGerald.vista;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gerald.tarea3dwesGerald.servicios.ServiciosFactory;
import com.gerald.tarea3dwesGerald.servicios.Sesion;
import com.gerald.tarea3dwesGerald.servicios.Sesion.Perfil;

import jakarta.annotation.PostConstruct;

@Component
public class FachadaPrincipal {
	
	@Autowired
	private ServiciosFactory factory;
	
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
	
	public void menuInvitado() {
		System.out.println("");
		System.out.println("***********************************************");
		System.out.println("Bienvenido al menu de gestión de un vivero");
		System.out.println("1.  Ver Plantas.");
		System.out.println("2.  Iniciar sesión.");
		System.out.println("0.  Cerrar aplicación.");
		System.out.println("Seleccione una opcion:");
	}

	public void menuAdministrador() {
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


	public void menuUsuarioRegistrado(String usuario) {
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
					/*
					//Login
					System.out.print("Introduce tu usuario: ");
					String usuario = teclado.nextLine();
					System.out.print("Introduce tu contraseña: ");
					String contraseña = teclado.nextLine();
					if (credendialesServ.Login(usuario, contraseña)) {
						s.setUsuario(usuario);
						s.setPerfil(Perfil.REGISTRADO);
						if (usuario.equals("admin")) {
							s.setPerfil(Perfil.ADMIN);
							this.mostrarMenuAdministrador(s);

						} else {
							this.mostrarMenuUsuarioRegistrado(s);
						}
					} else {
						System.out.println("Usuario o contraseña incorrectos");
					}*/

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

}
