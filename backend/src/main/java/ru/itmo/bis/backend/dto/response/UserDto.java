package ru.itmo.bis.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private UUID id;

    private String name;

    private String email;

    private String profilePictureUrl;

    private Set<RoleDto> roles;
}
