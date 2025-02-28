package ru.itmo.bis.backend.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.itmo.bis.backend.exception.api.UserNotFoundException;
import ru.itmo.bis.backend.model.User;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserRepository {

  private final ArtistRepository artistRepository;

  private final LandlordRepository landlordRepository;

  public Optional<User> findByEmail(String email) {
    var user = artistRepository.findByEmail(email)
        .orElseGet(() -> landlordRepository.findByEmail(email)
            .orElse(null));
    return Optional.ofNullable(user);
  }

  public boolean existsByEmail(String email) {
    return findByEmail(email).isPresent();
  }
}
