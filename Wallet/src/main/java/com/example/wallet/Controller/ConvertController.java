package com.example.wallet.Controller;

import com.example.wallet.Dto.ConvertDto;
import com.example.wallet.Service.ConvertService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер конвертирования валют
 *
 * @author Владислав Аппанов
 * @since 30.06.2023
 */
@RestController
@RequiredArgsConstructor
public class ConvertController {
	private final ConvertService convertService;

	@GetMapping("/user/convert")
	public ConvertDto convert(@ModelAttribute ConvertDto convertDto, @RequestHeader("Authorization") String jwtToken) {
		return convertService.convert(convertDto, jwtToken);
	}
}
