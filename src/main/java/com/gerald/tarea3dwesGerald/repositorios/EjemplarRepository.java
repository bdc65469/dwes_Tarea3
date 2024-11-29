package com.gerald.tarea3dwesGerald.repositorios;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gerald.tarea3dwesGerald.modelo.Ejemplar;
import com.gerald.tarea3dwesGerald.modelo.Planta;


@Repository
public interface EjemplarRepository  extends JpaRepository <Ejemplar, Long> {
	
	default Long ultimoIdEjemplarByPlanta (Planta p) {
		List<Ejemplar> lista = findAll();
		if (!lista.isEmpty()) {
			long ret = 0;
			for (Ejemplar e: lista) {
				if (e.getPlanta().getId().equals(p.getId())) {
					ret++;
				}
			}
			return ret+1;
		}
		
		return 1L;
		
	}
	
	 Set<Ejemplar> findByPlantaCodigo(String codigo);

}
