package com.example.wallet.Controller;

import com.example.wallet.Dto.RoleDto;
import com.example.wallet.Dto.UserDto;
import com.example.wallet.Service.RoleService;
import com.example.wallet.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import java.util.Set;
import java.util.function.Consumer;

/**
 * Контроллер пользователей
 *
 * @author Владислав Аппанов
 * @since 25.07.2023
 */
@RequiredArgsConstructor
@RestController
public class UserController {
	private final UserService userService;
	private final RoleService roleService;

	@GetMapping("/admin/users/{userId}")
	public ResponseEntity<UserDto> getUser(@PathVariable Long userId) {
		try {
			return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
		} catch (UsernameNotFoundException e){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/admin/users/{id}")
	public ResponseEntity deactivateUser(@PathVariable Long userId) {
		try {
			userService.disableUser(userId);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (UsernameNotFoundException e){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	private record RoleRequest(Long userId, String role) {};


	@DeleteMapping("/admin/roles/")
	public ResponseEntity<String> removeRole(@RequestBody RoleRequest roleRequest) {
		return editRole(roleRequest, roles -> roles.remove(roleService.getRole(roleRequest.role)));
	}

	@PutMapping("/admin/roles/")
	public ResponseEntity<String> addRole(@RequestBody RoleRequest roleRequest) {
		return editRole(roleRequest, roles -> roles.add(roleService.getRole(roleRequest.role)));
	}

	private ResponseEntity<String> editRole(RoleRequest roleRequest, Consumer<Set<RoleDto>> action) {
		try {
			userService.editRole(roleRequest.userId, action);
			return ResponseEntity.ok().build();
		} catch (UsernameNotFoundException | IllegalArgumentException e){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
}
