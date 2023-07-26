package com.example.currencyloader.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Dto курса валют
 *
 * @author Владислав Аппанов
 * @since 18.07.2023
 */
@Data
@AllArgsConstructor
public class ExchangeDto {
	private String currencyFrom;

	private String currencyTo;

	private Float value;
}
