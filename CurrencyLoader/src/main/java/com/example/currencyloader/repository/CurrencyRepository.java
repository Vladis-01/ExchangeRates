package com.example.currencyloader.repository;

import com.example.currencyloader.model.CurrencyName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий валюты
 *
 * @author Владислав Аппанов
 * @since 17.07.2023
 */
@Repository
public interface CurrencyRepository extends JpaRepository<CurrencyName, Long> {
}
