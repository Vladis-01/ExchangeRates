package com.example.currencyloader.service;

import com.example.currencyloader.Dto.CurrencyListDto;
import com.example.currencyloader.Dto.CurrencyNameDto;
import com.example.currencyloader.Dto.ExchangeDto;
import com.example.currencyloader.Dto.ExchangeLiveListDto;
import com.example.currencyloader.enums.Source;
import com.example.currencyloader.model.CurrencyName;
import com.example.currencyloader.repository.CurrencyRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Сервис валюты
 *
 * @author Владислав Аппанов
 * @since 17.07.2023
 */

@Service
@RequiredArgsConstructor
public class CurrencyService {
	private final CurrencyRepository currencyRepository;
	private final IPlaygroundCurrencies playgroundCurrencies;
	private final ModelMapper modelMapper;
	private final KafkaTemplate<Long, List<ExchangeDto>> kafkaStarshipTemplate;

	@Value("${APILAYER_CURRENCY_APIKEY}")
	private String apiKey;

	private List<CurrencyName> saveCurrencyName() {
		CurrencyListDto currencyListDto = playgroundCurrencies.getNamesMap(apiKey);
		List<CurrencyName> currenciesName = new ArrayList<>();
		if(currencyListDto.isSuccess()) {
			currenciesName = currencyRepository.saveAll(toCurrency(
				currencyListDto
					.getCurrencies()
					.keySet()
					.stream()
					.map(CurrencyNameDto::new)
					.toList()
			));
		}
		return currenciesName;
	}

	private List<CurrencyName> toCurrency(List<CurrencyNameDto> currencies) {
		return currencies.stream().map(name -> modelMapper.map(name, CurrencyName.class)).toList();
	}

	/**
	 * Каждые 2 часа подгружаем со стороннего сервиса актуальную информацию о курсе валют и отправляем в Kafka
	 */
	@Scheduled(fixedDelayString = "${playground.delay}")
	public void downloadExchanges() {
		for(Source sourceEnum : Source.values()) {
			List<CurrencyName> currenciesName = currencyRepository.findAll();
			if(currenciesName.isEmpty()) {
				currenciesName = saveCurrencyName();
			}
			StringBuilder currenciesStrBuilder = new StringBuilder();
			for(String currencyName : currenciesName.stream().map(CurrencyName::getName).toList()) {
				currenciesStrBuilder.append(currencyName + ", ");
			}

			ExchangeLiveListDto exchangeLiveListDto = playgroundCurrencies.getExchanges(
				apiKey,
				sourceEnum.name(),
				currenciesStrBuilder.toString()
			);

			if(exchangeLiveListDto.isSuccess()) {
				List<ExchangeDto> exchanges = new ArrayList<>();
				String source = exchangeLiveListDto.getSource();
				exchangeLiveListDto.getQuotes().forEach((key, value) -> exchanges.add(
					new ExchangeDto(exchangeLiveListDto.getSource(),
					key.replaceFirst(source, ""),
					value)
				));
				kafkaStarshipTemplate.send("server.updateExchanges", exchanges);
			}
		}
	}
}
