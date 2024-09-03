package com.locadora.API.Repo;

import com.locadora.API.Modelo.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepo extends JpaRepository<Produto, Long> {
}
