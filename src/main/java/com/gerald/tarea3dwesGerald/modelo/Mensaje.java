package com.gerald.tarea3dwesGerald.modelo;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "mensajes")
public class Mensaje implements Serializable{
	

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime fechahora;
	
	@Lob
	private String mensaje;
	
	@ManyToOne
    @JoinColumn(name = "idEjemplar")
    private Ejemplar ejemplar;
	
	@ManyToOne
    @JoinColumn(name = "idPersona")
    private Persona persona;
	
	public Mensaje() {}

	public Mensaje(Long id, LocalDateTime fechahora, String mensaje) {
		super();
		this.id = id;
		this.fechahora = fechahora;
		this.mensaje = mensaje;
	}

	public Mensaje(LocalDateTime fechahora, String mensaje, Long idEjemplar, Long idPersona) {
		super();
		this.fechahora = fechahora;
		this.mensaje = mensaje;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getFechahora() {
		return fechahora;
	}

	public void setFechahora(LocalDateTime fechahora) {
		this.fechahora = fechahora;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	

	public Ejemplar getEjemplar() {
		return ejemplar;
	}

	public void setEjemplar(Ejemplar ejemplar) {
		this.ejemplar = ejemplar;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	
	

}
