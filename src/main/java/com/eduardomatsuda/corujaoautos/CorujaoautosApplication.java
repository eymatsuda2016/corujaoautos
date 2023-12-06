package com.eduardomatsuda.corujaoautos;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.eduardomatsuda.corujaoautos.domain.Categoria;
import com.eduardomatsuda.corujaoautos.repositories.CategoriaRepository;

@SpringBootApplication
public class CorujaoautosApplication implements CommandLineRunner {    
	@Autowired
	private CategoriaRepository categoriaRepository;

	public static void main(String[] args) {
		SpringApplication.run(CorujaoautosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria (null, "Autos e Carros");
		Categoria  cat2 = new Categoria (null, "Caminhões e Ônibus");
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));	
	}
}
