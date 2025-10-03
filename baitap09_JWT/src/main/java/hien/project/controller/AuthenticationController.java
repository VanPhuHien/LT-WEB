package hien.project.controller;

import hien.project.dto.JwtAuthenticationResponse;
import hien.project.dto.SignInRequest;
import hien.project.dto.SignUpRequest;
import hien.project.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

	private final AuthenticationService authenticationService;

	@PostMapping("/register")
	public ResponseEntity<JwtAuthenticationResponse> register(@RequestBody SignUpRequest request) {
		return ResponseEntity.ok(authenticationService.signup(request));
	}

	@PostMapping("/login")
	public ResponseEntity<JwtAuthenticationResponse> login(@RequestBody SignInRequest request) {
		return ResponseEntity.ok(authenticationService.signin(request));
	}
}