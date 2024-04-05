package com.algaworks.awpag.api.controller;

import com.algaworks.awpag.api.assembler.ParcelamentoAssembler;
import com.algaworks.awpag.api.model.ParcelamentoDTO;
import com.algaworks.awpag.api.model.input.ParcelamentoInputDTO;
import com.algaworks.awpag.domain.exception.NegocioException;
import com.algaworks.awpag.domain.model.Parcelamento;
import com.algaworks.awpag.domain.repository.ParcelamentoRepository;
import com.algaworks.awpag.domain.service.OperacoesParcelamentoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
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
    private final ParcelamentoAssembler parcelamentoAssembler;

    @GetMapping
    public List<ParcelamentoDTO> listar() {
        return parcelamentoAssembler.toCollectionModel(parcelamentoRepository.findAll());
    }

    @GetMapping("/{parcelamentoId}")
    public ResponseEntity<ParcelamentoDTO> buscar(@PathVariable Long parcelamentoId) {

        return parcelamentoRepository.findById(parcelamentoId)
                .map(parcelamentoAssembler::toModel)
                .map(ResponseEntity::ok)
                //.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
        /*findById retornará um Optional de parcelamento, então mapeamos esse Optional para transformar em um ResponseEntity usando
        map do próprio Optional, usando o Method Reference.
         */
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ParcelamentoDTO adicionar(@Valid @RequestBody ParcelamentoInputDTO parcelamentoInputDTO) {
        Parcelamento novoParcelamento = parcelamentoAssembler.toEntity(parcelamentoInputDTO);
        Parcelamento parcelamentoCadastrado = operacoesParcelamentoService.cadastrar(novoParcelamento);
        return parcelamentoAssembler.toModel(parcelamentoCadastrado);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<String> capturar(NegocioException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
