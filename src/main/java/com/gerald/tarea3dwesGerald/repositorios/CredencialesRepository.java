package com.gerald.tarea3dwesGerald.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gerald.tarea3dwesGerald.modelo.Credenciales;
import com.gerald.tarea3dwesGerald.modelo.Persona;

@Repository
public interface CredencialesRepository extends JpaRepository <Credenciales, Long>{
	
	 //Credenciales findByUsuarioAndPassword(String usuario, String password);
	 
	
	/**
	 * Comprueba si las credenciales introducidas son válidas, con el binary diferencia entre mayúsculas y minúsculas
	 * @param usuario Nombre de usuario
	 * @param password Contraseña del usuario
	 * @return Las credenciales correspondientes al usuario y contraseña buscada
	 */
	 @Query(value = "SELECT * FROM credenciales WHERE BINARY usuario = :usuario AND BINARY password = :password", nativeQuery = true)
	 Credenciales findByUsuarioAndPassword(@Param("usuario") String usuario, @Param("password") String password);
	 
	 /**
	  * Comprueba si existe un usuario
	  * @param usuario Nombre del usuario
	  * @return True si hay unas credenciales con el nombre de usuario buscado y false si no las hay
	  */
	 boolean existsByUsuario(String usuario);
	
	 /**
	  * Busca una persona por el nombre de usuario
	  * @param usuario Nombre de usuario
	  * @return La persona que tiene el usuario buscado
	  */
	 @Query("SELECT c.persona FROM Credenciales c WHERE c.usuario = :usuario")
	 Persona findPersonaByUsuario(@Param("usuario") String usuario);
	 
	 /**
	  * Devuelve las credenciales registradas ordenadas por usuario
	  * @return Una lista de credenciales
	  */
	 List<Credenciales> findAllByOrderByUsuarioAsc();

}
