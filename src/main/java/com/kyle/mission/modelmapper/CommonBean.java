package com.kyle.mission.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
 
@Configuration
public class CommonBean {
    
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}