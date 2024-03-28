package com.algaworks.awpag.api.controller;

import com.algaworks.awpag.domain.exception.NegocioException;
import com.algaworks.awpag.domain.model.Parcelamento;
import com.algaworks.awpag.domain.repository.ParcelamentoRepository;
import com.algaworks.awpag.domain.service.OperacoesParcelamentoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/parcelamentos")
public class ParcelamentoController {

    private final ParcelamentoRepository parcelamentoRepository;
    private final OperacoesParcelamentoService operacoesParcelamentoService;

    @GetMapping
    public List<Parcelamento> listar() {
        return parcelamentoRepository.findAll();
    }

    @GetMapping("/{parcelamentoId}")
    public ResponseEntity<Parcelamento> buscar(@PathVariable Long parcelamentoId) {
    //Faremos de uma forma mais enxuta
        return parcelamentoRepository.findById(parcelamentoId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
        /*findById retornará um Optional de parcelamento, então mapeamos esse Optional para transformar em um ResponseEntity usando
        map do próprio Optional, usando o Method Reference.
         */
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Parcelamento adicionar(@RequestBody Parcelamento parcelamento) {
        return operacoesParcelamentoService.cadastrar(parcelamento);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<String> capturar(NegocioException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
