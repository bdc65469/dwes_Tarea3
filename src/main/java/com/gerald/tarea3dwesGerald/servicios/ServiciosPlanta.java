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
	
	public void insertarPlanta (Planta p) {
		plantaRepo.saveAndFlush(p);
	}
	
	public List<Planta> listaPlantas(){
		return plantaRepo.findAll();
	}
	


}
