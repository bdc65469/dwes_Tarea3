package com.gerald.tarea3dwesGerald.servicios;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gerald.tarea3dwesGerald.modelo.Ejemplar;
import com.gerald.tarea3dwesGerald.modelo.Mensaje;
import com.gerald.tarea3dwesGerald.modelo.Persona;
import com.gerald.tarea3dwesGerald.repositorios.MensajeRepository;



@Service
public class ServiciosMensaje {
	
	@Autowired
	private MensajeRepository repoMensaje;
	
	@Autowired
	private ServiciosEjemplar servEjemplar;
	
	@Autowired
	private ServiciosPersona servPersona;

	public Mensaje crearMensaje(Mensaje m) {
		return repoMensaje.save(m);
	}

    public List<Mensaje> obtenerMensajesPorIdEjemplar(Long idejemplar) {
        return repoMensaje.findByEjemplarIdOrderByFechaHoraAsc(idejemplar);
    }
    
    public Mensaje crearMensaje (String mensaje, Long idEjemplar, String usuario) {
    	Ejemplar e = servEjemplar.obtenerEjemplarporId(idEjemplar);
    	Persona p = servPersona.obtenerPersonaPorUsuario(usuario);
    	
    	Mensaje nuevo = new Mensaje (LocalDateTime.now(), mensaje, e, p);
    	
    	return repoMensaje.saveAndFlush(nuevo);	
    }
    
    public List<Mensaje> obtenerMensajesPorPersona (Long id){
    	return repoMensaje.obtenerMensajesPorPersona(id);
    }
    
    public List<Mensaje> obtenerMensajesPorFecha(LocalDateTime fechaInicial, LocalDateTime fechaFinal){
    	return repoMensaje.obtenerMensajesPorRangoDeFecha(fechaInicial, fechaFinal);
    }
    
    public List<Mensaje> obtenerMensajesPorPlanta(String codigo) {
        return repoMensaje.obtenerMensajesPorPlanta(codigo);
    }
    
}
