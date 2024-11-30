package com.gerald.tarea3dwesGerald.servicios;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gerald.tarea3dwesGerald.modelo.Ejemplar;
import com.gerald.tarea3dwesGerald.modelo.Mensaje;
import com.gerald.tarea3dwesGerald.modelo.Persona;
import com.gerald.tarea3dwesGerald.modelo.Planta;
import com.gerald.tarea3dwesGerald.repositorios.EjemplarRepository;

import jakarta.persistence.EntityNotFoundException;


@Service
public class ServiciosEjemplar {
	
	
	@Autowired
	private EjemplarRepository repoEjemplar;
	
	@Autowired
	private ServiciosPlanta servPlanta;
	
	@Transactional
	public Ejemplar crearEjemplarYMensaje(Long plantaId, Persona persona) {
	    
	    Planta planta = servPlanta.obtenerPlantaporId(plantaId);	       
	    String nombreEjemplar = planta.getCodigo().toUpperCase() + repoEjemplar.ultimoIdEjemplarByPlanta(planta);
	    Ejemplar ejemplar = new Ejemplar(nombreEjemplar, planta);
    
	    Mensaje mensaje = new Mensaje(
	        LocalDateTime.now(),          
	        "Nuevo ejemplar de "+planta.getNombrecomun()+" creado.",                 
	        ejemplar,                     
	        persona                       
	    );

	    ejemplar.getMensajes().add(mensaje);
	 
	    repoEjemplar.save(ejemplar);

	    return ejemplar; 
	}
	
	public Set<Ejemplar> filtarEjemplaresPlanta(String codigo){
		return repoEjemplar.findByPlantaCodigo(codigo);
	}
	
	public List<Ejemplar> listadoEjemplares() {
		return repoEjemplar.findAll();
	}
	
	public Ejemplar obtenerEjemplarporId(Long id) {
		return repoEjemplar.findById(id).orElseThrow(() -> new EntityNotFoundException("Ejemplar no encontrada con el ID: " + id));
	}
}
