package com.gerald.tarea3dwesGerald.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gerald.tarea3dwesGerald.modelo.Mensaje;
import com.gerald.tarea3dwesGerald.repositorios.MensajeRepository;



@Service
public class ServiciosMensaje {
	
	@Autowired
	private MensajeRepository repoMensaje;

	public Mensaje crearMensaje(Mensaje m) {
		return repoMensaje.save(m);
	}

    public List<Mensaje> obtenerMensajesPorIdEjemplar(Long idejemplar) {
        return repoMensaje.findByEjemplarIdOrderByFechaHoraAsc(idejemplar);
    }
}
