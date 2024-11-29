package com.gerald.tarea3dwesGerald;


import org.springframework.boot.CommandLineRunner;

import com.gerald.tarea3dwesGerald.vista.FachadaPrincipal;


public class Principal implements CommandLineRunner {
	

	@Override
	public void run(String... args) throws Exception {
		
		FachadaPrincipal portal = FachadaPrincipal.getInstance();
		
		portal.mostrarMenuInvitado();
		
        
        
	}

}
