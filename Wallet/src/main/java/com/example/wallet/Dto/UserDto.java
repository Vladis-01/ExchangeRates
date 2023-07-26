package com.example.wallet.Dto;

import com.example.wallet.Model.Role;
import lombok.Data;

import java.util.Set;

/**
 * Dto пользователя
 *
 * @author Владислав Аппанов
 * @since 25.07.2023
 */
@Data
public class UserDto {
	private Long id;

	private String username;

	private String password;

	private Set<RoleDto> roles;
}
