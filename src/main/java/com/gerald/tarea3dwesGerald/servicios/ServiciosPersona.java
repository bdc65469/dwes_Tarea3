package com.gerald.tarea3dwesGerald.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gerald.tarea3dwesGerald.modelo.Credenciales;
import com.gerald.tarea3dwesGerald.modelo.Persona;
import com.gerald.tarea3dwesGerald.repositorios.CredencialesRepository;
import com.gerald.tarea3dwesGerald.repositorios.PersonaRepository;

@Service
public class ServiciosPersona {
	
	@Autowired
	PersonaRepository repoPersona;
	
	@Autowired
	CredencialesRepository repoCredenciales;
	
	public boolean existeEmail(String email) {
		return repoPersona.existsByEmail(email);
	}
	
	@Transactional // Asegura que ambas operaciones se hagan en una sola transacción
    public Persona crearUsuario(Persona persona, Credenciales credenciales) {
        // Guardar primero la persona
        persona = repoPersona.save(persona);

        // Asociar las credenciales con la persona
        credenciales.setPersona(persona);

        // Guardar las credenciales
        credenciales = repoCredenciales.save(credenciales);

        // Devolver la persona con sus credenciales asociadas
        return persona;
    }

}
