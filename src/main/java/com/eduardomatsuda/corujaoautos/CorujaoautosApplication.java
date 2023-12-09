package com.eduardomatsuda.corujaoautos;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.eduardomatsuda.corujaoautos.domain.Categoria;
import com.eduardomatsuda.corujaoautos.domain.Produto;
import com.eduardomatsuda.corujaoautos.repositories.CategoriaRepository;
import com.eduardomatsuda.corujaoautos.repositories.ProdutoRepository;

@SpringBootApplication
public class CorujaoautosApplication implements CommandLineRunner {    
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CorujaoautosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria (null, "Autos e Carros");
		Categoria  cat2 = new Categoria (null, "Caminhões e Ônibus");
		
		Produto p1 = new Produto(null, "Jogo de Velas VW SpaceFox", 120.00);
		Produto p2 = new Produto(null, "Jogo de Lampadas do Painel", 130.00);
		Produto p3 = new Produto(null, "Jogo de Cabos de Velas VW SpaceFox", 125.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));	
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
	}
}
