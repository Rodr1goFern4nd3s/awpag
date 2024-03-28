package com.algaworks.awpag.domain.service;

import com.algaworks.awpag.domain.exception.NegocioException;
import com.algaworks.awpag.domain.model.Cliente;
import com.algaworks.awpag.domain.model.Parcelamento;
import com.algaworks.awpag.domain.repository.ClienteRepository;
import com.algaworks.awpag.domain.repository.ParcelamentoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class OperacoesParcelamentoService {

    private final ParcelamentoRepository parcelamentoRepository;
    private final OperacoesClienteService operacoesClienteService;

    @Transactional
    public Parcelamento cadastrar(Parcelamento novoParcelamento) {
        if (novoParcelamento.getId() != null) {
            throw new NegocioException("Parcelamento a ser criado não deve possuir um código");
        }
        //Antes de chamar o save(), garantimos que o cliente existe com esta validação
        Cliente cliente = operacoesClienteService.buscar(novoParcelamento.getCliente().getId());

        /*Supondo que o cliente exista (cliente) temos ele acima com todos os dados carregados, completos
        basta atribuirmos ao novo parcelamento, já com os dados do cliente todos preenchidos.
         */
        novoParcelamento.setCliente(cliente);

        //Antes de salvar o parcelamento no repositório, atribui uma data de criação para ele!
        novoParcelamento.setDataCriacao(LocalDateTime.now());

        return parcelamentoRepository.save(novoParcelamento);
    }
}
