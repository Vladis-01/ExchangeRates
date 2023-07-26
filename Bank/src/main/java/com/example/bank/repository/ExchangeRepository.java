package com.example.bank.repository;

import com.example.bank.model.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Репозиторий курса валют-
 *
 * @author Владислав Аппанов
 * @since 28.06.2023
 */
@Repository
public interface ExchangeRepository extends JpaRepository<Exchange, Long> {
	Optional<Exchange> findByCurrencyFromAndCurrencyTo(String from, String to);
}
