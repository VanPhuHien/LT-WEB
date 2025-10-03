package hien.project.service;

import hien.project.dto.JwtAuthenticationResponse;
import hien.project.dto.SignInRequest;
import hien.project.dto.SignUpRequest;

public interface AuthenticationService {
	JwtAuthenticationResponse signup(SignUpRequest request);

	JwtAuthenticationResponse signin(SignInRequest request);
}