package ru.itmo.bis.backend.runner;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.itmo.bis.backend.constant.UserRole;
import ru.itmo.bis.backend.model.Role;
import ru.itmo.bis.backend.repository.RoleRepository;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class EnumTablesInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) {
        List<UserRole> defaultRoles = Arrays.asList(UserRole.ARTIST, UserRole.LANDLORD);
        for (UserRole roleName : defaultRoles) {
            roleRepository.findByName(roleName)
                    .orElseGet(() ->
                            roleRepository.save(Role.builder().name(roleName).build()));
        }
    }
}
