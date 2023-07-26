package com.example.wallet.Configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
 * Запись с ключами для JWT
 *
 * @author Владислав Аппанов
 * @since 30.06.2023
 */
@ConfigurationProperties(prefix ="rsa")
public record RsaProperties(RSAPrivateKey privateKey, RSAPublicKey publicKey) {
}