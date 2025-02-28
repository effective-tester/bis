package ru.itmo.bis.backend.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itmo.bis.backend.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class BisUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    var user = userRepository.findByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException(email));
    return UserPrincipal.create(user);
  }
}
