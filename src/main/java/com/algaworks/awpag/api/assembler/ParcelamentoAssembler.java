package com.algaworks.awpag.api.assembler;

import com.algaworks.awpag.api.model.ParcelamentoDTO;
import com.algaworks.awpag.api.model.input.ParcelamentoInputDTO;
import com.algaworks.awpag.domain.model.Parcelamento;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class ParcelamentoAssembler {

    private final ModelMapper modelMapper;

    public ParcelamentoDTO toModel(Parcelamento parcelamento){
        return modelMapper.map(parcelamento, ParcelamentoDTO.class);
    }

    public List<ParcelamentoDTO> toCollectionModel(List<Parcelamento> parcelamentos) {
        return parcelamentos.stream().map(this::toModel).toList();
    }

    public Parcelamento toEntity(ParcelamentoInputDTO parcelamentoInputDTO) {
        return modelMapper.map(parcelamentoInputDTO, Parcelamento.class);
    }
}
