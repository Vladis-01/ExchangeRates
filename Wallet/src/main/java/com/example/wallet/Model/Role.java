package com.example.wallet.Model;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Сущность роли пользователя
 *
 * @author Владислав Аппанов
 * @since 30.06.2023
 */
@Table(name="roles")
@Entity
@Data
public class Role {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="role_name")
	private String roleName;
}
