package com.example.currencyloader.Dto;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * Dto валюты
 *
 * @author Владислав Аппанов
 * @since 18.07.2023
 */
@Data
public class CurrencyListDto {
	private Map<String, String> currencies;

	private boolean success;
}
