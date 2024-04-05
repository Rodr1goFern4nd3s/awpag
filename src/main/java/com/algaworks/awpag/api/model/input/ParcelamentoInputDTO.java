package com.algaworks.awpag.api.model.input;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ParcelamentoInputDTO {

    @NotBlank
    @Size(max = 20)
    private String descricao;

    @NotNull
    @Positive
    private BigDecimal valorTotal;

    @NotNull
    @Positive
    @Max(value = 12)
    private Integer quantidadeParcelas;

    @Valid
    @NotNull
    private ClienteIdInput clienteId;
}
