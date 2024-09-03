package com.locadora.API;

import com.locadora.API.Modelo.Categoria;
import com.locadora.API.Modelo.Genero;
import com.locadora.API.Repo.CategoriaRepo;
import com.locadora.API.Repo.GeneroRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class LocadoraApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(LocadoraApiApplication.class, args);
	}

	@Configuration
	public static class DataLoader {

		@Bean
		public CommandLineRunner loadData(CategoriaRepo categoriaRepo, GeneroRepo generoRepo) {
			return args -> {
				// Verifica e insere categorias se não existirem
				crieCategoriaINE(categoriaRepo, "Livro");
				crieCategoriaINE(categoriaRepo, "Filme");
				crieCategoriaINE(categoriaRepo, "Jogo");

				// Generos
				crieGeneroINE(generoRepo, "Ficção Científica");
				crieGeneroINE(generoRepo, "Fantasia");
				crieGeneroINE(generoRepo, "Romance");
				crieGeneroINE(generoRepo, "Mistério");
				crieGeneroINE(generoRepo, "Terror");
				crieGeneroINE(generoRepo, "Aventura");
				crieGeneroINE(generoRepo, "Drama");
				crieGeneroINE(generoRepo, "Biografia");
				crieGeneroINE(generoRepo, "Comédia");
				crieGeneroINE(generoRepo, "Thriller/Suspense");
				crieGeneroINE(generoRepo, "Policial");
				crieGeneroINE(generoRepo, "Ação");
				crieGeneroINE(generoRepo, "Documentário");
				crieGeneroINE(generoRepo, "Animação");
				crieGeneroINE(generoRepo, "Musical");
				crieGeneroINE(generoRepo, "FPS");

			};
		}

		private void crieCategoriaINE (CategoriaRepo categoriaRepo, String nomeCategoria) { // INE = IF NOT EXISTS
			if (categoriaRepo.findByNome(nomeCategoria).isEmpty()) {
				Categoria categoria = new Categoria();
				categoria.setNome(nomeCategoria);
				categoriaRepo.save(categoria);
			}
		}
		private void crieGeneroINE (GeneroRepo generoRepo, String nomeGenero){
			if (generoRepo.findByNome(nomeGenero).isEmpty()) {
				Genero genero = new Genero();
				genero.setNome(nomeGenero);
				generoRepo.save(genero);
			}
		}
	}
}
