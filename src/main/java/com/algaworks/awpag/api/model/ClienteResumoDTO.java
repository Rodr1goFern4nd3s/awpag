package com.algaworks.awpag.api.model;

import lombok.Data;

@Data
public class ClienteResumoDTO {
    /*
    Este DTO vai retornar, determinar um breve resumo do cliente lá no payload
     */

    private Long id;
    private String nome;
}
