package com.gerald.tarea3dwesGerald.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gerald.tarea3dwesGerald.modelo.Planta;

@Repository
//Tipo de objeto
//Clave primaria
public interface PlantaRepository extends JpaRepository <Planta, Long> {
	
	/**
	 * Devuelve todas las plantas almacenadas ordenadas por nombrecomun
	 * @return Devuelve una lista de plantas
	 */
	List<Planta> findAllByOrderByNombrecomunAsc();
	
	/**
	 * Comprueba si existe un codigo de planta
	 * @param codigo Codigo a buscar
	 * @return true si existe. false si no existe
	 */
	boolean existsByCodigo(String codigo);
	
	/**
	 * Comprueba si existe un id de planta
	 * @param id id a buscar
	 * @return true si existe. false si no existe
	 */
	boolean existsById(Long id);

}
