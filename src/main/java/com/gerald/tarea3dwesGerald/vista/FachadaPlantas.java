package com.gerald.tarea3dwesGerald.vista;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gerald.tarea3dwesGerald.modelo.Planta;
import com.gerald.tarea3dwesGerald.servicios.ServiciosFactory;

@Component
public class FachadaPlantas {
	
	private Scanner teclado = new Scanner(System.in);
	@Autowired
	private ServiciosFactory factory;
	
	private void menuGestionarPlantas() {
		System.out.println("");
		System.out.println("***********************************************");
		System.out.println("Menú Gestión de Plantas");
		System.out.println("1.  Insertar nueva planta.");
		System.out.println("2.  Modificar una planta.");
		System.out.println("0.  Salir.");
		System.out.println("Seleccione una opcion:");
	}
	
	public void mostrarMenuGestionarPlantas() {
		int opcion = -1;
		do {
			this.menuGestionarPlantas();
			try {
				opcion = teclado.nextInt();
				teclado.nextLine();
				if (opcion < 0 || opcion > 2) {
					System.err.println("Opción fuera de rango. Inténtelo de nuevo.");
					continue;
				}

				switch (opcion) {
				case 1:
					// Insertar nueva planta
					String codigo = "";
					do {
						System.out.println(
								"Introduce el codigo de la planta (Sin números, espacios, dierisis o carácteres especiales)");
						codigo = teclado.nextLine();

						if (!factory.getComprobaciones().esCodigoValido(codigo)) {
							System.out.println(
									"Formato de codigo incorrecto. Recuerda que no puede contener números, espacios, dierisis o carácteres especiales");
						}

						if (factory.getServiciosPlanta().existeCodigoPlanta(codigo)) {
							System.out.println("Ya existe una planta con ese codigo");
						}

					} while (!factory.getComprobaciones().esCodigoValido(codigo) || factory.getServiciosPlanta().existeCodigoPlanta(codigo));

					String nombrecomun = "";
					do {
						System.out.println("Introduce el nombre común de la planta. ");
						nombrecomun = teclado.nextLine();

						if (!factory.getComprobaciones().nombreValido(nombrecomun)) {
							System.out.println("Nombre comun incorrecto. No puede contener número o solo espacios");
						}
					} while (!factory.getComprobaciones().nombreValido(nombrecomun));

					String nombrecientifico = "";
					do {
						System.out.println("Introduce el nombre científico de la planta");
						nombrecientifico = teclado.nextLine();

						if (!factory.getComprobaciones().nombreValido(nombrecientifico)) {
							System.out
									.println("Nombre científico incorrecto. No puede contener número o solo espacios");
						}
					} while (!factory.getComprobaciones().nombreValido(nombrecientifico));

					Planta nueva = new Planta(codigo.toUpperCase(), nombrecomun, nombrecientifico);

					if (factory.getServiciosPlanta().añadirPlanta(nueva)!=null) {
						System.out.println("Planta añadida correctamente");
					} else {
						System.out.println("No se pudo añadir la planta");
					}

					break;
				case 2:
					// Modificar una planta
					
					if (factory.getServiciosPlanta().listaPlantas().size() == 0) {
						System.out.println("No hay plantas registradas");
					} else {
						System.out.println("LISTA DE PLANTAS");
						System.out.printf("%-10s %-30s %-40s %20s%n", "ÍNDICE", "NOMBRE COMÚN", "NOMBRE CIENTÍFICO", "CODIGO");
						System.out.println("----------------------------------------------------------------------------------------------------------------------------");
						for (int i = 0; i < factory.getServiciosPlanta().listaPlantas().size(); i++) {
							System.out.printf("%-10s %-30s %-40s %20s%n", i+1, factory.getServiciosPlanta().listaPlantas().get(i).getNombrecomun(), 
									factory.getServiciosPlanta().listaPlantas().get(i).getNombrecientifico(), 
									factory.getServiciosPlanta().listaPlantas().get(i).getCodigo());
						}
						int numFinal = factory.getServiciosPlanta().listaPlantas().size();
						int num = 0;
						do {
							try {
								System.out.println("Introduce el numero de la planta que quieres modificar: ");
								num = teclado.nextInt();
								teclado.nextLine();
								if (num < 1 || num > numFinal) {
									System.err.println(
											"Numero incorrecto. Tienes que introducir un número entre el 1 y el "
													+ numFinal);
								} else {
									String actnombrecomun = "";
									do {
										System.out.println("Introduce el nuevo nombre común de la planta. ");
										actnombrecomun = teclado.nextLine();

										if (!factory.getComprobaciones().nombreValido(actnombrecomun)) {
											System.out.println(
													"Nombre comun incorrecto. No puede contener número o solo espacios");
										}
									} while (!factory.getComprobaciones().nombreValido(actnombrecomun));

									String nombrecien = "";
									do {
										System.out.println("Introduce el nuevo nombre científico de la planta");
										nombrecien = teclado.nextLine();

										if (!factory.getComprobaciones().nombreValido(nombrecien)) {
											System.out.println(
													"Nombre científico incorrecto. No puede contener número o solo espacios");
										}
									} while (!factory.getComprobaciones().nombreValido(nombrecien));
									if (factory.getServiciosPlanta().actualizarPlanta(factory.getServiciosPlanta().listaPlantas().get(num - 1),
											actnombrecomun, nombrecien) != null) {
										System.out.println("Actualizado correctamente");
									} else {
										System.err.println("No se ha podido actualizar");
									}
								}
							} catch (InputMismatchException e) {
								System.err.println("Error. Debes introducir un número");
								teclado.nextLine(); // Limpiar el buffer del scanner
							}

						} while (num < 1 || num > numFinal);
					}

					break;

				case 0:
					//Cerrar menu
					return;
				}
			} catch (InputMismatchException e) {
				System.err.println("Entrada inválida. Por favor, ingrese un número entero.");
				teclado.next();
			}

		} while (opcion != 0);

	}
	
}
