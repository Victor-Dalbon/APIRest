package com.locadora.API.Repo;


import com.locadora.API.Modelo.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PedidoRepo extends JpaRepository<Pedido, Long> {

}
