package com.example.currencyloader.configuration;

import com.example.currencyloader.Dto.ExchangeDto;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.LongSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Конфиг продюсера Kafka
 *
 * @author Владислав Аппанов
 * @since 18.07.2023
 */
@Configuration
public class KafkaProducerConfig {

	@Value("${kafka.server}")
	private String kafkaServer;

	@Value("${kafka.producer.id}")
	private String kafkaProducerId;

	@Bean
	public Map<String, Object> producerConfigs() {
		Map<String, Object> props = new HashMap<>();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		props.put(ProducerConfig.CLIENT_ID_CONFIG, kafkaProducerId);
		return props;
	}

	@Bean
	public ProducerFactory<Long, List<ExchangeDto>> producerExchangesFactory() {
		return new DefaultKafkaProducerFactory<>(producerConfigs());
	}

	@Bean
	public KafkaTemplate<Long, List<ExchangeDto>> kafkaTemplate() {
		KafkaTemplate<Long, List<ExchangeDto>> template = new KafkaTemplate<>(producerExchangesFactory());
		template.setMessageConverter(new StringJsonMessageConverter());
		return template;
	}
}
