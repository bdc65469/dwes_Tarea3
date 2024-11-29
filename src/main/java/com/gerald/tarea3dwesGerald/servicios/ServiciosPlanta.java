package com.gerald.tarea3dwesGerald.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gerald.tarea3dwesGerald.modelo.Planta;
import com.gerald.tarea3dwesGerald.repositorios.PlantaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ServiciosPlanta {

	@Autowired
	private PlantaRepository plantaRepo;

	public Planta añadirPlanta(Planta p) {
		return plantaRepo.saveAndFlush(p);
	}

	public List<Planta> listaPlantas() {
		return plantaRepo.findAll();
	}

	public boolean existeCodigoPlanta(String codigo) {
		return plantaRepo.existsByCodigo(codigo);
	}

	public Planta actualizarPlanta(Planta actualizar, String nombre, String nombrecientifico) {
	
		actualizar.setNombrecomun(nombre);
		actualizar.setNombrecientifico(nombrecientifico);
		
		return plantaRepo.save(actualizar);
		
	}
	
	public Planta obtenerPlantaporId(Long id) {
		return plantaRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Planta no encontrada con el ID: " + id));
	}

}
