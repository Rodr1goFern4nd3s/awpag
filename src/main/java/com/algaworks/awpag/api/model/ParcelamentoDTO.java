package com.algaworks.awpag.api.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
public class ParcelamentoDTO {
    /*
    Esta classe vai representar o nosso modelo Parcelamento
     */

    private Long id;
    //private String nomeCliente;
    private ClienteResumoDTO cliente;
    private String descricao;
    private BigDecimal valorTotal;
    private Integer quantidadeParcelas;
    private OffsetDateTime dataCriacao;
}
