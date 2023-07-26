package com.example.bank.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Dto для конвертирования валют
 *
 * @author Владислав Аппанов
 * @since 18.07.2023
 */
@Data
@AllArgsConstructor
public class ConvertDto {
	private String currencyFrom;

	private String currencyTo;

	private Float value;
}
