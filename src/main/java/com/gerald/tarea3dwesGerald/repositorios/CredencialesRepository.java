package com.gerald.tarea3dwesGerald.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gerald.tarea3dwesGerald.modelo.Credenciales;

@Repository
public interface CredencialesRepository extends JpaRepository <Credenciales, Long>{
	
	 Credenciales findByUsuarioAndPassword(String usuario, String password);
	 
	 boolean existsByUsuario(String usuario);
	

}
