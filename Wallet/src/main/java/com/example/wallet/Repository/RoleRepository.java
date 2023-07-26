package com.example.wallet.Repository;

import com.example.wallet.Model.Role;
import com.example.wallet.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * TODO Class Description
 *
 * @author Владислав Аппанов
 * @since 26.07.2023
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByRoleName(String name);
}
