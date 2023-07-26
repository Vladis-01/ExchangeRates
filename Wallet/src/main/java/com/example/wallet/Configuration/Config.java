package com.example.wallet.Configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * TODO Class Description
 *
 * @author Владислав Аппанов
 * @since 25.07.2023
 */
@Configuration
public class Config {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
