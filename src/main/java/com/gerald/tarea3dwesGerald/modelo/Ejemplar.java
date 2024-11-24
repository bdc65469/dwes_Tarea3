package com.gerald.tarea3dwesGerald.modelo;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "ejemplares")
public class Ejemplar {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	
	@ManyToOne
    @JoinColumn(name = "idplanta", nullable = false)
	private Planta planta;
	
	@OneToMany(mappedBy = "ejemplar")
    private List<Mensaje> mensajes;
	
	public Ejemplar() {}
	
	public Ejemplar(Planta planta) {
		this.nombre = planta.getId()+"_"+this.id;
	}

	public Ejemplar(Long id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Planta getPlanta() {
		return planta;
	}

	public void setPlanta(Planta planta) {
		this.planta = planta;
	}

	@Override
	public String toString() {
		return "Ejemplar: " + nombre;
	}
	
	

}
