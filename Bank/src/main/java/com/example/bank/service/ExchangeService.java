package com.example.bank.service;

import com.example.bank.Dto.ConvertDto;
import com.example.bank.Dto.ExchangeDto;
import com.example.bank.model.Exchange;
import com.example.bank.repository.ExchangeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Сервис курса валют
 *
 * @author Владислав Аппанов
 * @since 28.06.2023
 */
@Service
@RequiredArgsConstructor
public class ExchangeService {
	private final ExchangeRepository exchangeRepository;
	private final ModelMapper modelMapper;

	public ConvertDto convert(ConvertDto convert) {
		ExchangeDto exchangeDto = modelMapper.map(
			exchangeRepository
				.findByCurrencyFromAndCurrencyTo(convert.getCurrencyFrom(), convert.getCurrencyTo())
				.orElseThrow(() -> new IllegalStateException("Валюта не найдена")), ExchangeDto.class
		);
		convert.setValue(convert.getValue() * exchangeDto.getValue());
		return convert;
	}

	@KafkaListener(id = "UpdateExchanges", topics = {"server.updateExchanges"}, containerFactory = "singleFactory")
	public void updateExchanges(List<ExchangeDto> exchanges) {
		if(exchanges.get(0) != null) {
			List<ExchangeDto> exchangesFromRepository = exchangeRepository
			.findAll()
			.stream()
			.map(e -> modelMapper.map(e, ExchangeDto.class))
			.toList();

			exchanges.forEach(exchange -> {
				exchangesFromRepository.stream().filter(exchangeFromRepository ->
					exchange.getCurrencyFrom().equals(exchangeFromRepository.getCurrencyFrom())
						&& exchange.getCurrencyTo().equals(exchangeFromRepository.getCurrencyTo())
				).findFirst().ifPresent(findExchange -> exchange.setId(findExchange.getId()));
			});

			exchangeRepository.saveAll(toExchange(exchanges));
		}
	}

	private List<Exchange> toExchange(List<ExchangeDto> exchanges) {
		return exchanges.stream().map(exchange -> modelMapper.map(exchange, Exchange.class)).toList();
	}
}
