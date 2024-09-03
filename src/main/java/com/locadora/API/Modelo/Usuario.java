package com.locadora.API.Modelo;



import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;



@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idUsuario;

    @NotBlank(message = "Nome não pode ser vazio")
    @Column(nullable = false)
    private String nome;


    @NotBlank(message = "Email não pode ser vazio")
    @Column(nullable = false)
    private String email;

    @NotBlank(message = "Senha não pode estar vazia")
    @Column(nullable = false)
    private String senha;

    @NotBlank(message = "Telefone não pode estar vazio")
    @Column(nullable = false, length = 13)
    private String telefone;

    @NotBlank(message = "Endereco não pode estar vazio")
   @Column
   private String endereco;

    @JsonBackReference
    @OneToOne(mappedBy = "usuario")
    @PrimaryKeyJoinColumn
    private Pedido pedido;


    // GETTERs e SETTERs

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}
