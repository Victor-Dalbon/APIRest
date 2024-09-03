package com.locadora.API.Modelo;

import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.CreationTimestamp;


import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPedido;

    @CreationTimestamp
    @Column
    private Timestamp timestamp;

    @NotNull
    @Column(nullable = false, length = 45)
    private String metodo_pagamento;


    @OneToOne
    @JoinColumn(name = "idProduto", referencedColumnName = "idProduto")
    private Produto produto;

    @OneToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    // GETTERs E SETTERs

    public BigDecimal getValor() {
        return BigDecimal.valueOf(produto.getPreco());
    }
    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }


    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getMetodo_pagamento() {
        return metodo_pagamento;
    }

    public void setMetodo_pagamento(String metodo_pagamento) {
        this.metodo_pagamento = metodo_pagamento;
    }


    public Produto getProduto() {
        return produto;
    }
    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + idPedido +
                ", usuario: " + getUsuario().getNome() + '\'' +
                ", idPedido: " + idPedido +
                ", produto: " + produto +
                '}';
    }



}
