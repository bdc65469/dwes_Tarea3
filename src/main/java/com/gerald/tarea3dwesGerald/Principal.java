package com.gerald.tarea3dwesGerald;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import com.gerald.tarea3dwesGerald.servicios.*;
import com.gerald.tarea3dwesGerald.modelo.*;

public class Principal implements CommandLineRunner {
	
	@Autowired
	ServiciosPlanta servPlant;
	
	@Autowired
	ServiciosEjemplar servEjemplar;

	@Override
	public void run(String... args) throws Exception {
		//Este es el main
		System.out.println("INI");
		System.out.println("FIN");
	}

}
