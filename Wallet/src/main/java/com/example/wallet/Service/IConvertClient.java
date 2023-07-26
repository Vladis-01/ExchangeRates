package com.example.wallet.Service;

import com.example.wallet.Dto.ConvertDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

/**
 * Интерфейс для взаимодействия с сервисом Bank
 *
 * @author Владислав Аппанов
 * @since 28.06.2023
 */
@Component
@FeignClient(name = "data", url = "${feign.bank.url}")
public interface IConvertClient {
	@PostMapping("/exchange")
	ConvertDto convert(@RequestBody ConvertDto convert, @RequestHeader("Authorization") String jwtToken);
}
