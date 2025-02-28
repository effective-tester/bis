package ru.itmo.bis.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itmo.bis.backend.constant.UserRole;
import ru.itmo.bis.backend.dto.request.auth.LoginRequest;
import ru.itmo.bis.backend.dto.request.auth.RegisterRequest;
import ru.itmo.bis.backend.dto.response.SignInDto;
import ru.itmo.bis.backend.dto.response.SignUpDto;
import ru.itmo.bis.backend.exception.api.LocationNotFoundException;
import ru.itmo.bis.backend.exception.security.EmailAlreadyInUseException;
import ru.itmo.bis.backend.exception.security.RoleNotFoundException;
import ru.itmo.bis.backend.model.Artist;
import ru.itmo.bis.backend.model.Landlord;
import ru.itmo.bis.backend.model.Role;
import ru.itmo.bis.backend.model.User;
import ru.itmo.bis.backend.repository.ArtistRepository;
import ru.itmo.bis.backend.repository.LandlordRepository;
import ru.itmo.bis.backend.repository.LocationRepository;
import ru.itmo.bis.backend.repository.RoleRepository;
import ru.itmo.bis.backend.repository.UserRepository;
import ru.itmo.bis.backend.security.JwtTokenProvider;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

  private static final UserRole DEFAULT_ROLE = UserRole.ARTIST;

  private final LocationRepository locationRepository;

  private final AuthenticationManager authenticationManager;

  private final ArtistRepository artistRepository;

  private final LandlordRepository landlordRepository;

  private final UserRepository userRepository;

  private final RoleRepository roleRepository;

  private final PasswordEncoder passwordEncoder;

  private final JwtTokenProvider tokenProvider;

  @Transactional
  public SignUpDto register(RegisterRequest registerRequest) {
    validateEmailAvailability(registerRequest.getEmail());
    UserRole role = registerRequest.getRole();
    User user = createAndAssignRoleToUser(registerRequest, role);
    saveUser(user, role);
    return new SignUpDto();
  }

  @Transactional
  public SignInDto authenticate(LoginRequest loginRequest) {
    var authentication = createAuthentication(loginRequest);
    String jwt = tokenProvider.generateToken(authentication);
    return new SignInDto(jwt);
  }

  private Authentication createAuthentication(LoginRequest loginRequest) {
    return authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            loginRequest.getEmail(),
            loginRequest.getPassword()
        )
    );
  }

  private void validateEmailAvailability(String email) {
    if (userRepository.existsByEmail(email)) {
      throw new EmailAlreadyInUseException();
    }
  }

  private User createAndAssignRoleToUser(RegisterRequest registerRequest, UserRole userRole) {
    Role role = findRole(userRole);
    User user = buildUser(registerRequest, userRole);
    user.getRoles().add(role);
    return user;
  }

  private User buildUser(RegisterRequest registerRequest, UserRole userRole) {
    var userBuilder = switch (userRole) {
      case LANDLORD -> Landlord.builder();
      case ARTIST -> Artist.builder();
    };
    return userBuilder
        .name(registerRequest.getUsername())
        .email(registerRequest.getEmail())
        .password(passwordEncoder.encode(registerRequest.getPassword()))
        .build();
  }

  private Role findRole(UserRole roleName) {
    var roleToFind = Optional.ofNullable(roleName)
        .orElse(DEFAULT_ROLE);
    return roleRepository.findByName(roleToFind)
        .orElseThrow(RoleNotFoundException::new);
  }

  private Optional<User> saveUser(User user, UserRole role) {
    if (role.equals(UserRole.ARTIST)) {
      user = artistRepository.save((Artist) user);
    } else if (role.equals(UserRole.LANDLORD)) {
      user = landlordRepository.save((Landlord) user);
    }
    return Optional.ofNullable(user);
  }
}


