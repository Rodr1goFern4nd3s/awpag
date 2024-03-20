package com.algaworks.awpag.api.controller;

import com.algaworks.awpag.domain.model.Cliente;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @GetMapping
    public List<Cliente> listar() {
        var cliente1 = new Cliente();
        cliente1.setId(1L);
        cliente1.setNome("Gilvan da Silva");
        cliente1.setEmail("gilvan@algaworks.com.br");
        cliente1.setTelefone("61945246852");

        var cliente2 = new Cliente();
        cliente2.setId(2L);
        cliente2.setNome("Emilia Pereira de Oliveira");
        cliente2.setEmail("emilia@algaworks.com.br");
        cliente2.setTelefone("61945452389");

        return Arrays.asList(cliente1, cliente2);
    }
}
