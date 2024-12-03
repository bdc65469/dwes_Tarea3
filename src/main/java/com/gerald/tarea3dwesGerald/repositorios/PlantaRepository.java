package com.gerald.tarea3dwesGerald.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gerald.tarea3dwesGerald.modelo.Planta;

@Repository
//Tipo de objeto
//Clave primaria
public interface PlantaRepository extends JpaRepository <Planta, Long> {
	
	List<Planta> findAllByOrderByNombrecomunAsc();
	
	boolean existsByCodigo(String codigo);
	
	boolean existsById(Long id);

}
