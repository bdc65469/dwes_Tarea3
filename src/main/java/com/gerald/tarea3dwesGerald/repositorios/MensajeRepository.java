package com.gerald.tarea3dwesGerald.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gerald.tarea3dwesGerald.modelo.Mensaje;


@Repository
public interface MensajeRepository extends JpaRepository <Mensaje, Long> {

	@Query("SELECT m FROM Mensaje m JOIN m.ejemplar e JOIN m.persona p WHERE e.id = :idEjemplar ORDER BY m.fechahora ASC")
    List<Mensaje> findByEjemplarIdOrderByFechaHoraAsc(@Param("idEjemplar") Long idEjemplar);
}
