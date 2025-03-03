package ru.itmo.bis.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itmo.bis.backend.dto.request.auth.LoginRequest;
import ru.itmo.bis.backend.dto.request.auth.RegisterRequest;
import ru.itmo.bis.backend.dto.response.SignInDto;
import ru.itmo.bis.backend.dto.response.SignUpDto;
import ru.itmo.bis.backend.service.AuthService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  @PostMapping("/signup")
  public ResponseEntity<SignUpDto> registerUser(
      @Validated @RequestBody RegisterRequest registerRequest
  ) {
    SignUpDto message = authService.register(registerRequest);
    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(message);
  }

  @GetMapping("/signin")
  public ResponseEntity<SignInDto> authenticateUser(
      @Validated @RequestBody LoginRequest loginRequest
  ) {
    SignInDto signInDto = authService.authenticate(loginRequest);
    return ResponseEntity.ok(signInDto);
  }
}
