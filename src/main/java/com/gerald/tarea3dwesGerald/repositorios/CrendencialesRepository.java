package com.gerald.tarea3dwesGerald.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gerald.tarea3dwesGerald.modelo.Credenciales;


@Repository
public interface CrendencialesRepository extends JpaRepository <Credenciales, Long>{

}
