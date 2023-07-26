package com.example.bank.controller;

import com.example.bank.Dto.ConvertDto;
import com.example.bank.Dto.ExchangeDto;
import com.example.bank.service.ExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * Контроллер курса валют
 *
 * @author Владислав Аппанов
 * @since 28.06.2023
 */
@RestController
@RequiredArgsConstructor
public class ExchangeController {
	private final ExchangeService exchangeService;

	@PostMapping("/exchange")
	public ConvertDto getExchange(@RequestBody ConvertDto convertDto, @RequestHeader("Authorization") String jwtToken) {
		return exchangeService.convert(convertDto);
	}
}
