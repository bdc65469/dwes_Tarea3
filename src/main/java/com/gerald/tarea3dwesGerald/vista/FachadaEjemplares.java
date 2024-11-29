package com.gerald.tarea3dwesGerald.vista;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gerald.tarea3dwesGerald.modelo.Ejemplar;
import com.gerald.tarea3dwesGerald.modelo.Mensaje;
import com.gerald.tarea3dwesGerald.modelo.Planta;
import com.gerald.tarea3dwesGerald.servicios.ServiciosFactory;
import com.gerald.tarea3dwesGerald.servicios.Sesion;

@Component
public class FachadaEjemplares {
	
	Scanner teclado = new Scanner(System.in);
	
	@Autowired
	private ServiciosFactory factory;
	
	public void menuGestionarEjemplares() {
		System.out.println("");
		System.out.println("***********************************************");
		System.out.println("Menú Gestión de Ejemplares");
		System.out.println("1.  Crear ejemplar de una planta.");
		System.out.println("2.  Ver ejemplares de planta/as.");
		System.out.println("3.  Ver mensajes de un ejemplar.");
		System.out.println("0.  Salir.");
		System.out.println("Seleccione una opcion:");
	}
	
	public void mostrarMenuGestionEjemplares(Sesion s) {
		int opcion = -1;
		do {
			this.menuGestionarEjemplares();
			try {
				opcion = teclado.nextInt();
				teclado.nextLine();
				if (opcion < 0 || opcion > 3) {
					System.err.println("Opción fuera de rango. Inténtelo de nuevo.");
					continue;
				}

				switch (opcion) {
				case 1:
					// Crear ejemplar
					if (factory.getServiciosPlanta().listaPlantas().size() == 0) {
						System.out.println("No hay plantas registradas.");
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
								System.out.println("Introduce el numero de la planta que quieres crear un ejemplar: ");
								num = teclado.nextInt();
								teclado.nextLine();
								if (num < 1 || num > numFinal) {
									System.err.println(
											"Numero incorrecto. Tienes que introducir un número entre el 1 y el "
													+ numFinal);
								} else {
									Planta escogida = factory.getServiciosPlanta().listaPlantas().get(num - 1);
									if (factory.getServiciosEjemplar().crearEjemplarYMensaje(escogida.getId(), factory.getServiciosPersona().obtenerPersonaPorUsuario(s.getUsuario()))!=null) {
										System.out.println("Ejemplar creado correctamente");
									}else {
										System.out.println("No se ha podido crear el ejemplar");
									}

								}
							} catch (InputMismatchException e) {
								System.err.println("Error. Debes introducir un número");
								teclado.nextLine(); // Limpiar el buffer del scanner
							}

						} while (num < 1 || num > numFinal);
					}
					break;
				case 2:
					// Ver ejemplares de plantas
					ArrayList<Integer> ejemplaresPlantas = new ArrayList<Integer>();
					if (factory.getServiciosPlanta().listaPlantas().size() == 0) {
						System.out.println("No hay plantas registradas.");
					} else {
						System.out.println("LISTA DE PLANTAS");
						System.out.printf("%-10s %-30s %-40s %20s%n", "ÍNDICE", "NOMBRE COMÚN", "NOMBRE CIENTÍFICO", "CODIGO");
						System.out.println("----------------------------------------------------------------------------------------------------------------------------");
						for (int i = 0; i < factory.getServiciosPlanta().listaPlantas().size(); i++) {
							System.out.printf("%-10s %-30s %-40s %20s%n", i+1, factory.getServiciosPlanta().listaPlantas().get(i).getNombrecomun(), 
									factory.getServiciosPlanta().listaPlantas().get(i).getNombrecientifico(), 
									factory.getServiciosPlanta().listaPlantas().get(i).getCodigo());
						}
						int numFinal1 = factory.getServiciosPlanta().listaPlantas().size();
						int num1 = 0;
						do {
							try {
								System.out.println(
										"Introduce el numero de la/s planta/as que quieres ver los ejemplares. Pulsa 0 cuando quieras salir: ");
								num1 = teclado.nextInt();
								teclado.nextLine();
								if (num1 < 0 || num1 > numFinal1) {
									System.err.println(
											"Numero incorrecto. Tienes que introducir un número entre el 1 y el "
													+ numFinal1 + " o el 0 para salir");
								} else {
									if (num1 == 0) {
										break;
									}
									if (ejemplaresPlantas.contains(num1)) {
										System.out.println("Ya introdujiste esa planta;");
									} else {
										ejemplaresPlantas.add(num1);
									}
								}

							} catch (InputMismatchException e) {
								System.err.println("Error. Debes introducir un número");
								teclado.nextLine(); // Limpiar el buffer del scanner
							}

						} while (num1 != 0);
						for (Integer i : ejemplaresPlantas) {
							if (factory.getServiciosEjemplar().filtarEjemplaresPlanta(factory.getServiciosPlanta().listaPlantas().get(i - 1).getCodigo()).size() == 0) {
								System.out.println("No hay ejemplares de la planta "
										+ factory.getServiciosPlanta().listaPlantas().get(i - 1).getNombrecomun());
							} else {
								System.out.println("EJEMPLARES PLANTA -->  "
										+ factory.getServiciosPlanta().listaPlantas().get(i - 1).getNombrecomun());
								System.out.printf("%-50s %-40s %-60s %n", "NOMBRE EJEMPLAR", "NºMENSAJES", "FECHA DEL ÚLTIMO MENSAJE");
								System.out.println("-------------------------------------------------------------------------------------------------------------------");
								for (Ejemplar e : factory.getServiciosEjemplar()
										.filtarEjemplaresPlanta(factory.getServiciosPlanta().listaPlantas().get(i - 1).getCodigo())) {
									
									if (factory.getServiciosMensaje().obtenerMensajesPorIdEjemplar(e.getId()).size() == 0) {
										System.out.printf("%-50s %-40s %-60s %n", e.getNombre(), 0, "NULA");
									} else {
										List<Mensaje> listaMensajes = factory.getServiciosMensaje().obtenerMensajesPorIdEjemplar(e.getId());
										LocalDateTime ultimo = factory.getServiciosMensaje().obtenerMensajesPorIdEjemplar(e.getId()).get(0).getFechahora();										
										for (int m=0; m<listaMensajes.size(); m++) {
											if(ultimo.isBefore(listaMensajes.get(m).getFechahora())) {
												ultimo = listaMensajes.get(m).getFechahora();
											}
										}
										
										System.out.printf("%-50s %-40s %-60s %n", e.getNombre() , listaMensajes.size(), factory.getComprobaciones().formatoFecha(ultimo));
										
									}
								}
								System.out.println("");
							}

						}
					}
					break;
				case 3:
					// Ver mensajes de un ejemplar
					
					if (factory.getServiciosEjemplar().listadoEjemplares().size() == 0) {
						System.out.println("No hay ejemplares registrados.");
					} else {
						System.out.println("LISTA DE EJEMPLARES");
						System.out.printf("%-10s %-30s%n", "ÍNDICE", "NOMBRE");
						System.out.println("---------------------");
						for (int i = 0; i < factory.getServiciosEjemplar().listadoEjemplares().size(); i++) {
							System.out.printf("%-10s %-30s%n", i+1, factory.getServiciosEjemplar().listadoEjemplares().get(i).getNombre());
						}
						int numFinalEjem = factory.getServiciosEjemplar().listadoEjemplares().size();
						int numEjem = 0;
						do {
							try {
								System.out.println("Introduce el numero del que quieres ver sus mensajes: ");
								numEjem = teclado.nextInt();
								teclado.nextLine();
								if (numEjem < 1 || numEjem > numFinalEjem) {
									System.err.println(
											"Numero incorrecto. Tienes que introducir un número entre el 1 y el "
													+ numFinalEjem);
								} else {
									if (factory.getServiciosMensaje()
											.obtenerMensajesPorIdEjemplar(
													factory.getServiciosEjemplar().listadoEjemplares().get(numEjem - 1).getId())
											.size() == 0) {
										System.out.println("No hay mensajes del ejemplar "
												+ factory.getServiciosEjemplar().listadoEjemplares().get(numEjem - 1).getNombre());
									} else {
										System.out.println("Mensajes del ejemplar "
												+ factory.getServiciosEjemplar().listadoEjemplares().get(numEjem - 1).getNombre() + "\n");
										System.out.printf("%-80s %-30s %-20s %20s%n", "MENSAJE", "FECHA", "AUTOR", "EMAIL AUTOR");
										System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------");
										for (Mensaje m : factory.getServiciosMensaje().obtenerMensajesPorIdEjemplar(
												factory.getServiciosEjemplar().listadoEjemplares().get(numEjem - 1).getId())) {
											
											System.out.printf("%-80s %-30s %-20s %20s%n",m.getMensaje(),
													factory.getComprobaciones().formatoFecha(m.getFechahora()),
													factory.getServiciosPersona().obtenerPersonaPorId(m.getPersona().getId()).getNombre(),
													factory.getServiciosPersona().obtenerPersonaPorId(m.getPersona().getId()).getEmail());
										}
									}
								}
							} catch (InputMismatchException e) {
								System.err.println("Error. Debes introducir un número");
								teclado.nextLine(); // Limpiar el buffer del scanner
							}

						} while (numEjem < 1 || numEjem > numFinalEjem);
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
