package com.aluracursos.challenge3_literalura;

import com.aluracursos.challenge3_literalura.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Challenge3LiteraluraApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Challenge3LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		principal.interaccionUsuario();
	}

}
