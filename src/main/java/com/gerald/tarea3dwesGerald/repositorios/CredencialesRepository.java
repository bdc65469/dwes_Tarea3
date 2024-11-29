package com.gerald.tarea3dwesGerald.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gerald.tarea3dwesGerald.modelo.Credenciales;
import com.gerald.tarea3dwesGerald.modelo.Persona;

@Repository
public interface CredencialesRepository extends JpaRepository <Credenciales, Long>{
	
	 Credenciales findByUsuarioAndPassword(String usuario, String password);
	 
	 boolean existsByUsuario(String usuario);
	
	 @Query("SELECT c.persona FROM Credenciales c WHERE c.usuario = :usuario")
	 Persona findPersonaByUsuario(@Param("usuario") String usuario);

}
