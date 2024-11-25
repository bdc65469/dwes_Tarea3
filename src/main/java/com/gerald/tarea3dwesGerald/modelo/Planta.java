package com.gerald.tarea3dwesGerald.modelo;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="plantas")
public class Planta {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String nombrecomun;
	private String nombrecientifico;
	
	@OneToMany(mappedBy = "planta", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Ejemplar> ejemplares;
	
	public Planta() {}
	
	public Planta(Long id, String nombrecomun, String nombrecientifico) {
		super();
		this.id = id;
		this.nombrecomun = nombrecomun;
		this.nombrecientifico = nombrecientifico;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long Id) {
		this.id = Id;
	}

	public String getNombrecomun() {
		return nombrecomun;
	}

	public void setNombrecomun(String nombrecomun) {
		this.nombrecomun = nombrecomun;
	}

	public String getNombrecientifico() {
		return nombrecientifico;
	}

	public void setNombrecientifico(String nombrecientifico) {
		this.nombrecientifico = nombrecientifico;
	}

	public List<Ejemplar> getEjemplares() {
		return ejemplares;
	}

	public void setEjemplares(List<Ejemplar> ejemplares) {
		this.ejemplares = ejemplares;
	}

	@Override
	public String toString() {
		return this.nombrecomun;
	}

}
