package com.algaworks.awpag.common;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    /*
    Esta classe é para adicionarmos configurações do ModelMapper
     */

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
