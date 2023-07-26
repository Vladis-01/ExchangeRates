package com.example.wallet.Controller;

import com.example.wallet.Dto.UserDto;
import com.example.wallet.Model.User;
import com.example.wallet.Service.TokenService;
import com.example.wallet.Service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

/**
 * Контроллер аутентификации
 *
 * @author Владислав Аппанов
 * @since 30.06.2023
 */
@RestController
public class AuthController {

	private final TokenService tokenService;
	private final AuthenticationManager authManager;
	private final UserService usrDetailsService;


	public AuthController(TokenService tokenService, AuthenticationManager authManager,
						  UserService usrDetailsService) {
		super();
		this.tokenService = tokenService;
		this.authManager = authManager;
		this.usrDetailsService = usrDetailsService;
	}


	record LoginRequest(String username, String password) {};
	record LoginResponse(String message, String access_jwt_token, String refresh_jwt_token) {};
	@PostMapping("/login")
	public LoginResponse login(@RequestBody LoginRequest request) {

		UsernamePasswordAuthenticationToken authenticationToken =
			new UsernamePasswordAuthenticationToken(request.username, request.password);
		Authentication auth = authManager.authenticate(authenticationToken);

		User user = (User) usrDetailsService.loadUserByUsername(request.username);
		String access_token = tokenService.generateAccessToken(user);
		String refresh_token = tokenService.generateRefreshToken(user);

		return new LoginResponse("User with email = "+ request.username + " successfully logined!"

			, access_token, refresh_token);
	}

	@PostMapping("/registration")
	public ResponseEntity registration(@RequestBody UserDto userDto) {
		try {
			usrDetailsService.createOrUpdateUser(userDto);
			return ResponseEntity.ok().build();
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
	}

	record RefreshTokenResponse(String access_jwt_token, String refresh_jwt_token) {};
	@GetMapping("/token/refresh")
	public RefreshTokenResponse refreshToken(HttpServletRequest request) {
		String headerAuth = request.getHeader("Authorization");
		String refreshToken = headerAuth.substring(7, headerAuth.length());

		String email = tokenService.parseToken(refreshToken);
		User user = (User) usrDetailsService.loadUserByUsername(email);
		String access_token = tokenService.generateAccessToken(user);
		String refresh_token = tokenService.generateRefreshToken(user);

		return new RefreshTokenResponse(access_token, refresh_token);
	}
}