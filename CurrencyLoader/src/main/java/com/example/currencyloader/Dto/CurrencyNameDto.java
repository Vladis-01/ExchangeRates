package com.example.currencyloader.Dto;

import lombok.Data;

/**
 * Dto названия валют
 *
 * @author Владислав Аппанов
 * @since 17.07.2023
 */
@Data
public class CurrencyNameDto {
	private Long id;
	private String name;

	public CurrencyNameDto(String name) {
		this.name = name;
	}
}
