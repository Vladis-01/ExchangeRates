package com.example.bank.Dto;

import lombok.Data;

/**
 * Dto для курса валют
 *
 * @author Владислав Аппанов
 * @since 28.06.2023
 */
@Data
public class ExchangeDto {
	private Long id;

	private String currencyFrom;

	private String currencyTo;

	private Float value;
}
