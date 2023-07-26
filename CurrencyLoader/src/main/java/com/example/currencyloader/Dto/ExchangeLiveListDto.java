package com.example.currencyloader.Dto;

import lombok.Data;

import java.util.Map;

/**
 * Dto со списком курса валют
 *
 * @author Владислав Аппанов
 * @since 18.07.2023
 */
@Data
public class ExchangeLiveListDto {
	private Map<String, Float> quotes;

	private String source;

	private boolean success;
}
