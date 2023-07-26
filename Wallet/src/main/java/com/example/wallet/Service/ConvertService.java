package com.example.wallet.Service;

import com.example.wallet.Dto.ConvertDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Сервис конвертации
 *
 * @author Владислав Аппанов
 * @since 18.07.2023
 */
@Service
@RequiredArgsConstructor
public class ConvertService {
	private final IConvertClient convertClient;

	public ConvertDto convert(ConvertDto convertDto, String jwtToken) {
		return convertClient.convert(convertDto, jwtToken);
	}
}
