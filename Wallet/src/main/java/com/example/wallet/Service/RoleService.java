package com.example.wallet.Service;

import com.example.wallet.Dto.RoleDto;
import com.example.wallet.Repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

/**
 * TODO Class Description
 *
 * @author Владислав Аппанов
 * @since 26.07.2023
 */
@Service
@AllArgsConstructor
public class RoleService {
	private final RoleRepository roleRepository;
	private final ModelMapper modelMapper;

	public RoleDto getRole(String role) {
		return modelMapper.map(
			roleRepository.findByRoleName(role).orElseThrow(() -> new IllegalArgumentException(role + " not found")),
			RoleDto.class
		);
	}
}
