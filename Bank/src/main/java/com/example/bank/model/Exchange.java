package com.example.bank.model;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Модель курса валют
 *
 * @author Владислав Аппанов
 * @since 28.06.2023
 */
@Data
@Entity
@Table(name="exchanges")
public class Exchange {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String currencyFrom;

	private String currencyTo;

	private Double value;
}
