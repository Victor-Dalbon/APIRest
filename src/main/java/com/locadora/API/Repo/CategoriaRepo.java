package com.locadora.API.Repo;

import com.locadora.API.Modelo.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriaRepo extends JpaRepository<Categoria, Long> {

    Optional<Categoria> findByNome(String nome);
}
