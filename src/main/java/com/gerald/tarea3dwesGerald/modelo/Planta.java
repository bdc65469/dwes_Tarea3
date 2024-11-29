package com.gerald.tarea3dwesGerald.modelo;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="plantas")
public class Planta implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column (unique=true)
	private String codigo;
	
	@Column (length=100)
	private String nombrecomun;
	
	@Column (length=100)
	private String nombrecientifico;
	
	@OneToMany(mappedBy = "planta", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Ejemplar> ejemplares = new LinkedList<Ejemplar>();
	
	public Planta() {}
	
	public Planta(Long id, String nombrecomun, String nombrecientifico) {
		super();
		this.id = id;
		this.nombrecomun = nombrecomun;
		this.nombrecientifico = nombrecientifico;
	}
	

	public Planta(String codigo, String nombrecomun, String nombrecientifico) {
		super();
		this.codigo = codigo;
		this.nombrecomun = nombrecomun;
		this.nombrecientifico = nombrecientifico;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long Id) {
		this.id = Id;
	}
	

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
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
