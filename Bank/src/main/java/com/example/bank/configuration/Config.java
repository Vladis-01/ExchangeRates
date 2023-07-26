package com.example.bank.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Конфиг с бинами
 *
 * @author Владислав Аппанов
 * @since 28.06.2023
 */
@Configuration
public class Config {
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
