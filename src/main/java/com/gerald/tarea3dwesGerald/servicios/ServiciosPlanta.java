package com.gerald.tarea3dwesGerald.servicios;

import java.util.List;
import java.util.Optional;

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

	//Quede aqui
	public Planta actualizarPlanta(Long id, Planta plantaNueva) {
		// Verificar si la planta con el ID existe
		if (plantaRepo.existsById(id)) {
			// Obtener la planta existente
			Optional<Planta> plantaExistente = plantaRepo.findById(id);
					

			// Actualizar los campos de la planta existente con los nuevos datos
			plantaExistente.setNombre(plantaNueva.getNombrecomun());
			plantaExistente.setDescripcion(plantaNueva.getNombrecientifico());

			// Guardar la planta actualizada
			return plantaRepository.save(plantaExistente);
		} else {
			throw new RuntimeException("La planta con el ID proporcionado no existe.");
		}
	}

}
