package com.algaworks.awpag.api.model.input;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ClienteIdInput {
    /*
    Esta classe serve apenas para pegarmos o Id dos clientes
     */

    @NotNull
    private Long id;
}
