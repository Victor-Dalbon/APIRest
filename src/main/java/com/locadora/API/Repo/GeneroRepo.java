package com.locadora.API.Repo;


import com.locadora.API.Modelo.Genero;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GeneroRepo extends JpaRepository<Genero, Long> {

    Optional<Genero> findByNome(String nome);
}
