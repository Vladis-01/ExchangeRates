package com.example.wallet.Repository;

import com.example.wallet.Model.User;
import org.bouncycastle.pqc.crypto.lms.LMOtsParameters;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Репозиторий пользователей
 *
 * @author Владислав Аппанов
 * @since 28.06.2023
 */
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String userName);
}
