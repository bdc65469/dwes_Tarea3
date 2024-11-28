package com.gerald.tarea3dwesGerald;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;

import com.gerald.tarea3dwesGerald.vista.FachadaPrincipal;


public class Principal implements CommandLineRunner {
	
	
	
	@Autowired
    private Environment environment;

	@Override
	public void run(String... args) throws Exception {
		
		FachadaPrincipal portal = FachadaPrincipal.getInstance();
		
		portal.mostrarMenuInvitado();
		
		String username = environment.getProperty("spring.security.user.name");
        String password = environment.getProperty("spring.security.user.password");
        String roles = environment.getProperty("spring.security.user.roles");
        
        //System.out.println(username);
	}

}
