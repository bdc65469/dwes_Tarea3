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
	private PersonaRepository repoPersona;
	
	@Autowired
	private CredencialesRepository repoCredenciales;
	
	public boolean existeEmail(String email) {
		return repoPersona.existsByEmail(email);
	}
	
	@Transactional 
    public Persona crearUsuario(Persona persona, Credenciales credenciales) {
       
        persona = repoPersona.save(persona);
        credenciales.setPersona(persona);
        credenciales = repoCredenciales.save(credenciales);
        return persona;
    }
	
	
	public Persona obtenerPersonaPorUsuario(String usuario) {
		return repoCredenciales.findPersonaByUsuario(usuario);
	}
	
	
	public Persona obtenerPersonaPorId(Long id) {
		return repoPersona.findPersonaById(id);
	}

}
