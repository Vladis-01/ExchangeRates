package com.example.currencyloader.model;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Сущность валюты
 *
 * @author Владислав Аппанов
 * @since 17.07.2023
 */
@Data
@Entity
@Table(name="exchanges")
public class CurrencyName {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
}
