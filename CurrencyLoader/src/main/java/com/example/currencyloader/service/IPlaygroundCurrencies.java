package com.example.currencyloader.service;

import com.example.currencyloader.Dto.CurrencyListDto;
import com.example.currencyloader.Dto.ExchangeDto;
import com.example.currencyloader.Dto.ExchangeLiveListDto;
import feign.Headers;
import feign.Param;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * Интерфейс для взаимодействия с со сторонним сервисом по предоставлению актуальной информации о курсе валют
 *
 * @author Владислав Аппанов
 * @since 17.07.2023
 */
@Component
@FeignClient(name = "currenciesName", url = "${feign.playground.url}")
public interface IPlaygroundCurrencies {
	@GetMapping(value = "/currency_data/list")
	CurrencyListDto getNamesMap(@RequestHeader("apikey") String apikey);

	@GetMapping(value = "/currency_data/live")
	ExchangeLiveListDto getExchanges(
		@RequestHeader("apikey") String apikey,
		@RequestParam String source,
		@RequestParam String currencies
	);
}
