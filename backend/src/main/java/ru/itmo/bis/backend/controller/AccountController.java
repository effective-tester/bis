package ru.itmo.bis.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itmo.bis.backend.dto.response.UserDto;
import ru.itmo.bis.backend.service.AccountService;


@RestController
@RequestMapping
@RequiredArgsConstructor
public class AccountController {

  private final AccountService accountService;

  @GetMapping("/me")
  public ResponseEntity<UserDto> me(Authentication authentication) {
    UserDto user = accountService.me(authentication);
    return ResponseEntity.ok(user);
  }
}
