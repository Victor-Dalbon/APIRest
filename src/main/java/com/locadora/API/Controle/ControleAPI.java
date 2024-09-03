package com.locadora.API.Controle;

import com.locadora.API.Excecao.ResourceNotFoundException;
import com.locadora.API.Modelo.*;
import com.locadora.API.Repo.*;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class ControleAPI {

    @Autowired
    private UserRepo usuarioRepo;

    @Autowired
    private CategoriaRepo categoriaRepo;

    @Autowired
    ProdutoRepo produtoRepo;

    @Autowired
    GeneroRepo generoRepo;

    @Autowired
    PedidoRepo pedidoRepo;


    @GetMapping(value = "/users")
    public List<Usuario> getUsers(){

        return usuarioRepo.findAll();

    }

    @GetMapping(value = "/produtos")
    public List<Produto> getProduto(){

        return produtoRepo.findAll();
    }
    @GetMapping(value = "/pedidos/ver")
    public List<Pedido> getPedido(){
        return pedidoRepo.findAll();
    }

    @PostMapping(value = "/criarUsuario")
    public Usuario criarUsuario(@Valid @RequestBody Usuario usuario){
        Optional<Usuario> usuarioExistente = usuarioRepo.findByNome(usuario.getNome());
        Optional<Usuario> emailExistente = usuarioRepo.findByEmail(usuario.getEmail());
        Optional<Usuario> telefoneExistente = usuarioRepo.findByTelefone(usuario.getTelefone());

        if (!usuarioExistente.isEmpty()){
            throw new IllegalArgumentException("Esse nome já está cadastrado");
        } else if (!emailExistente.isEmpty()) {
            throw new IllegalArgumentException("Esse email já está cadastrado");
        } else if (!telefoneExistente.isEmpty()){
            throw new IllegalArgumentException("Esse telefone já está cadastrado");
        }
        return usuarioRepo.save(usuario);
    }

    @PostMapping(value = "/criarProduto")
    public ResponseEntity<String> criarProduto(@RequestBody Produto produto){
        // Validar se a categoria existe
        Categoria categoria = categoriaRepo.findById(produto.getCategoria().getIdCategoria())
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada com id: " + produto.getCategoria().getIdCategoria()));
        produto.setCategoria(categoria);

        // Validar se os gêneros existem, se houver
        if (produto.getGenero() != null) {
            List<Genero> generos = produto.getGenero().stream()
                    .map(g -> generoRepo.findById(g.getIdGenero())
                            .orElseThrow(() -> new ResourceNotFoundException("Gênero não encontrado com id: " + g.getIdGenero())))
                    .collect(Collectors.toList());
            produto.setGenero(generos);
        }

        produtoRepo.save(produto);
        return ResponseEntity.ok("Produto criado com sucesso.");
    }

    @DeleteMapping(value = "/deleteUsuario/{idUsuario}")
    public String deleteUser(@PathVariable long idUsuario){
        Usuario deleteUser = usuarioRepo.findById(idUsuario).get();
        usuarioRepo.delete(deleteUser);
        return "Deletando";
    }

    @DeleteMapping(value = "/deleteCategoria/{idCategoria}")
    public String deleteCategoria(@PathVariable long idCategoria){
        Categoria deleteCategoria = categoriaRepo.findById(idCategoria).get();
        categoriaRepo.delete(deleteCategoria);
        return "Deletando a categoria" + categoriaRepo.findByNome(deleteCategoria.getNome());
    }
    @DeleteMapping(value = "/deletePedido/{idPedido}")
    public String deletePedido(@PathVariable long idPedido){
        Pedido deletePedido = pedidoRepo.findById(idPedido).get();
        pedidoRepo.delete(deletePedido);
        return "Deletendo o pedido";
    }


    @PostMapping(value = "/pedidos/criarPedido")
    public String criarPedido(@RequestBody Pedido pedido){
        Pedido ped = pedido;
        System.out.println(ped);
        pedidoRepo.save(pedido);
        return "Pedido Criado" + ped;
    }

}
