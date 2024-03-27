package com.algaworks.awpag.api.controller;

import com.algaworks.awpag.domain.exception.NegocioException;
import com.algaworks.awpag.domain.model.Cliente;
import com.algaworks.awpag.domain.repository.ClienteRepository;
import com.algaworks.awpag.domain.service.OperacoesClienteService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final OperacoesClienteService operacoesClienteService;

    private final ClienteRepository clienteRepository;

    @GetMapping
    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscar(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);

        if(cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get());
        }

        return ResponseEntity.notFound().build();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
        return operacoesClienteService.salvar(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizar(@Valid @PathVariable Long id, @RequestBody Cliente cliente) {
        if(!clienteRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        cliente.setId(id);
        cliente = operacoesClienteService.salvar(cliente);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if(!clienteRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        operacoesClienteService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<String> capturar(NegocioException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
