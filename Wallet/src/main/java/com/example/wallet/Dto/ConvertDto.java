package com.example.wallet.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Dto для конвертации
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
