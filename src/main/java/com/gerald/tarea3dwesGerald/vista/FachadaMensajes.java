package com.gerald.tarea3dwesGerald.vista;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gerald.tarea3dwesGerald.modelo.Mensaje;
import com.gerald.tarea3dwesGerald.servicios.ServiciosFactory;
import com.gerald.tarea3dwesGerald.servicios.Sesion;


@Component
public class FachadaMensajes {

	private Scanner teclado = new Scanner(System.in);
	@Autowired
	private ServiciosFactory factory;

	public void menuGestionarMensajes() {
		System.out.println("");
		System.out.println("***********************************************");
		System.out.println("Menú Gestión de Mensajes");
		System.out.println("1.  Crear mensaje.");
		System.out.println("2.  Filtrar mensajes por persona.");
		System.out.println("3.  Filtrar mensajes por un rango de fechas.");
		System.out.println("4.  Filtrar mensajes por planta.");
		System.out.println("0.  Salir.");
		System.out.println("Seleccione una opcion:");
	}

	public void mostrarMenuGestionarMensajes(Sesion s) {
		int opcion = -1;
		do {
			this.menuGestionarMensajes();
			try {
				opcion = teclado.nextInt();
				teclado.nextLine();
				if (opcion < 0 || opcion > 4) {
					System.err.println("Opción fuera de rango. Inténtelo de nuevo.");
					continue;
				}

				switch (opcion) {
				case 1:
					// Crear mensaje
					if (factory.getServiciosEjemplar().listadoEjemplares().size() == 0) {
						System.out.println("No hay ejemplares registrados");
					} else {
						System.out.println("LISTA DE EJEMPLARES");
						System.out.printf("%-10s %-30s%n", "ÍNDICE", "NOMBRE");
						System.out.println("---------------------");
						for (int i = 0; i < factory.getServiciosEjemplar().listadoEjemplares().size(); i++) {
							System.out.printf("%-10s %-30s%n", i + 1,
									factory.getServiciosEjemplar().listadoEjemplares().get(i).getNombre());
						}
						int numFinal = factory.getServiciosEjemplar().listadoEjemplares().size();
						int num = 0;
						do {
							try {

								System.out
										.println("Introduce el numero del ejemplar que quieres añadirle un mensaje: ");
								num = teclado.nextInt();
								teclado.nextLine();
								if (num < 1 || num > numFinal) {
									System.err.println(
											"Numero incorrecto. Tienes que introducir un número entre el 1 y el "
													+ numFinal);
								} else {
									String mensaje = "";
									do {

										System.out.println("Introduce el mensaje que quieres añadir: ");
										mensaje = teclado.nextLine();

										if (!factory.getComprobaciones().esMensajeValido(mensaje)) {
											System.out.println("Formato de mensaje no correcto.");
										} else {

											if (factory.getServiciosMensaje()
													.crearMensaje(mensaje, factory.getServiciosEjemplar()
															.listadoEjemplares().get(num - 1).getId(),
															s.getUsuario()) != null) {
												System.out.println("Mensaje añadido correctamente.");
											} else {
												System.err.println("No se ha podido añadir el mensaje.");
											}

										}

									} while (!factory.getComprobaciones().esMensajeValido(mensaje));

								}
							} catch (InputMismatchException e) {
								System.err.println("Error. Debes introducir un número");
								teclado.nextLine(); // Limpiar el buffer del scanner
							}

						} while (num < 1 || num > numFinal);
					}
					break;
				case 2:
					// Filtrar mensajes por persona
					String usuario = "";
					do {
						System.out.println("Introduce el usuario que quieres ver sus mensajes: ");
						usuario = teclado.nextLine();
						if (usuario.length() == 0) {
							System.out.println("Por favor introduce un usuario.");
						} else {
							if (!factory.getServiciosCredenciales().existeUsuario(usuario)) {
								System.out.println("No existe ese usuario");
							} else {
								List <Mensaje> listaMensajes = factory.getServiciosMensaje()
										.obtenerMensajesPorPersona(factory.getServiciosPersona().obtenerPersonaPorUsuario(usuario).getId());
								if (listaMensajes.isEmpty()) {
									System.out.println("El usuario " + usuario + " no ha escrito ningun mensaje");
								} else {
									System.out.println("Mensajes del usuario " + usuario + " :");
									System.out.printf("%-80s %-30s %-20s %20s%n", "MENSAJE", "FECHA", "EJEMPLAR",
											"AUTOR");
									System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------");
									for (Mensaje m : listaMensajes) {
										System.out.printf("%-80s %-30s %-20s %20s %n", m.getMensaje(),
												factory.getComprobaciones().formatoFecha(m.getFechahora()),
												m.getEjemplar().getNombre(),
												m.getPersona().getNombre());
									}
								}
							}
						}

					} while (!factory.getServiciosCredenciales().existeUsuario(usuario) || usuario.length() == 0);

					break;
				case 3:
					
					// Filtrar mensajes por un rango de fechas
					LocalDateTime fechaInicial = null;
					LocalDateTime fechaFinal = null;
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm:ss");
					LocalDateTime fechaHoraActual = LocalDateTime.now();
					do {
						try {
							System.out.print("Introduce la fecha inicial (dd/MM/yy HH:mm:ss): ");
							String inputInicial = teclado.nextLine();
							fechaInicial = LocalDateTime.parse(inputInicial, formatter);

							System.out.print("Introduce la fecha final (dd/MM/yy HH:mm:ss). Si no introduces nada, cogerá la fecha y hora actuales: ");
							String inputFinal = teclado.nextLine();
							
							//Si no introducimos ninguna fecha final, pone la actual
							if (inputFinal.trim().isEmpty()) {
								fechaFinal = LocalDateTime.now();
							} else {
								fechaFinal = LocalDateTime.parse(inputFinal, formatter);
							}

							// Verificar que ambas fechas sean anteriores o iguales a la fecha actual
							if (fechaInicial.isAfter(fechaHoraActual)) {
								System.err.println("La fecha inicial no puede ser posterior a la fecha y hora actual.");
								continue;
							}

							// Verificar que la fecha inicial sea menor que la fecha final
							if (fechaInicial.isAfter(fechaFinal)) {
								System.err.println("La fecha inicial debe ser anterior a la fecha final.");
								continue;
							}
							break;

						} catch (DateTimeParseException e) {
							System.err.println("Formato incorrecto. Asegúrate de usar el formato dd/MM/yy HH:mm:ss.");
						}

					} while (true);

					List<Mensaje> listaMensajesFecha = factory.getServiciosMensaje().obtenerMensajesPorFecha(fechaInicial, fechaFinal);
					if (listaMensajesFecha.isEmpty()) {
						System.out.println("No hay mensajes entre el " + factory.getComprobaciones().formatoFecha(fechaInicial)
								+ " y el " + factory.getComprobaciones().formatoFecha(fechaFinal));
					} else {
						System.out.printf("%-80s %-30s %-20s %20s%n", "MENSAJE", "FECHA", "EJEMPLAR", "AUTOR");
						System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");
						for (Mensaje m : listaMensajesFecha) {
							System.out.printf("%-80s %-30s %-20s %20s%n", m.getMensaje(),
									factory.getComprobaciones().formatoFecha(m.getFechahora()),
									m.getEjemplar().getNombre(),
									m.getPersona().getNombre());
						}
					}

					break;
				case 4:
					// Filtrar mensajes por planta
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
						int numPlanta = factory.getServiciosPlanta().listaPlantas().size();
						int numP = 0;
						do {
							try {
								System.out.println("Introduce el índice de la planta que quieres ver sus mensajes: ");
								numP = teclado.nextInt();
								teclado.nextLine();
								if (numP < 1 || numP > numPlanta) {
									System.out.println(
											"Numero incorrecto. Tienes que introducir un número entre el 1 y el "
													+ numPlanta);
								} else {
									List<Mensaje> listaMensajes = factory.getServiciosMensaje()
											.obtenerMensajesPorPlanta(factory.getServiciosPlanta().listaPlantas().get(numP - 1).getCodigo());
									if (listaMensajes.isEmpty()) {
										System.out.println("Esta planta no tiene mensajes");
									} else {
										System.out.println("Mensajes de la planta "
												+ factory.getServiciosPlanta().listaPlantas().get(numP - 1).getNombrecomun() + ": ");
										System.out.printf("%-80s %-30s %-20s %20s%n", "MENSAJE", "FECHA", "EJEMPLAR", "AUTOR");
										System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
										for (Mensaje m : listaMensajes) {
											System.out.printf("%-80s %-30s %-20s %20s%n", m.getMensaje(),
													factory.getComprobaciones().formatoFecha(m.getFechahora()),
													m.getEjemplar().getNombre(),
													m.getPersona().getNombre());
										}
									}
								}

							} catch (InputMismatchException e) {
								System.err.println("Error. Debes introducir un número");
								teclado.nextLine(); // Limpiar el buffer del scanner
							}

						} while (numP < 1 || numP > numPlanta);
					}
					break;

				case 0:
					// Cerrar menu
					return;
				}
			} catch (InputMismatchException e) {
				System.err.println("Entrada inválida. Por favor, ingrese un número entero.");
				teclado.next();
			}

		} while (opcion != 0);

	}

}
