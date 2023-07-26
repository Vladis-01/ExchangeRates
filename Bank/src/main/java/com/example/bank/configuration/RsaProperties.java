package com.example.bank.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
 * Запись с ключами для JWT
 *
 * @author Владислав Аппанов
 * @since 01.07.2023
 */
@ConfigurationProperties(prefix ="rsa")
public record RsaProperties(RSAPrivateKey privateKey, RSAPublicKey publicKey) {
}