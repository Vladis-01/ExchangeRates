package com.example.wallet.Service;

import com.example.wallet.Dto.RoleDto;
import com.example.wallet.Dto.UserDto;
import com.example.wallet.Model.Role;
import com.example.wallet.Model.User;
import com.example.wallet.Repository.RoleRepository;
import com.example.wallet.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Сервис пользователей
 *
 * @author Владислав Аппанов
 * @since 28.06.2023
 */
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
	private final UserRepository userRepository;
	private final RoleService roleService;
	private final ModelMapper modelMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByUsername(username).orElseThrow(
			() -> new UsernameNotFoundException(username + " not authorized.")
		);
	}

	public UserDto getUserById(Long userId) throws UsernameNotFoundException {
		return modelMapper.map(userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("User " +
			"with ID = " + userId + " not found")),	UserDto.class);
	}

	public boolean createOrUpdateUser(UserDto userDto) {
		if(userDto.getId() == null && userRepository.findByUsername(userDto.getUsername()).isPresent()) {
			throw new IllegalArgumentException("User with the same name already exists");
		}
		User userFromDb = userRepository.save(modelMapper.map(userDto, User.class));
		return userFromDb.getId() != null;
	}

	public void disableUser(Long userId) {
		userRepository.findById(userId).ifPresent(user -> {
			user.setEnabled(false);
			userRepository.save(user);
		});
	}

	public void editRole(Long userId, Consumer<Set<RoleDto>> action)
		throws IllegalArgumentException, UsernameNotFoundException
	{
		UserDto userDto = getUserById(userId);
		action.accept(userDto.getRoles());
	//	userDto.getRoles().add(roleService.getRole(role));
		createOrUpdateUser(userDto);
	}
}
