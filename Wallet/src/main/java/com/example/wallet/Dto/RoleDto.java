package com.example.wallet.Dto;

import jakarta.persistence.Column;
import lombok.Data;

/**
 * TODO Class Description
 *
 * @author Владислав Аппанов
 * @since 26.07.2023
 */
@Data
public class RoleDto {
	private Long id;

	private String roleName;
}
