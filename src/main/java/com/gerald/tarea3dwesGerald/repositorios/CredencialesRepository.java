package com.gerald.tarea3dwesGerald.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gerald.tarea3dwesGerald.modelo.Credenciales;
import com.gerald.tarea3dwesGerald.modelo.Persona;

@Repository
public interface CredencialesRepository extends JpaRepository <Credenciales, Long>{
	
	 //Credenciales findByUsuarioAndPassword(String usuario, String password);
	 
	 //Para que distinga entre Mayus y Minus en el usuario y contraseña
	 @Query(value = "SELECT * FROM credenciales WHERE BINARY usuario = :usuario AND BINARY password = :password", nativeQuery = true)
	 Credenciales findByUsuarioAndPassword(@Param("usuario") String usuario, @Param("password") String password);
	 
	 boolean existsByUsuario(String usuario);
	
	 @Query("SELECT c.persona FROM Credenciales c WHERE c.usuario = :usuario")
	 Persona findPersonaByUsuario(@Param("usuario") String usuario);
	 

}
