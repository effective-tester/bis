package ru.itmo.bis.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itmo.bis.backend.dto.response.UserDto;
import ru.itmo.bis.backend.exception.api.UserNotFoundException;
import ru.itmo.bis.backend.mapper.UserMapper;
import ru.itmo.bis.backend.model.User;
import ru.itmo.bis.backend.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final UserMapper userMapper;

    private final UserRepository userRepository;

    @Transactional
    public UserDto me(Authentication authentication) {
        String email = authentication.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
        return userMapper.toDto(user);
    }
}
