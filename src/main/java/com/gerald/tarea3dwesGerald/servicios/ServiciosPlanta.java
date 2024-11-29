package com.gerald.tarea3dwesGerald.servicios;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gerald.tarea3dwesGerald.modelo.Planta;
import com.gerald.tarea3dwesGerald.repositorios.PlantaRepository;

@Service
public class ServiciosPlanta {

	@Autowired
	PlantaRepository plantaRepo;
	@Autowired
	ServiciosEjemplar servEjemplar;

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

}
