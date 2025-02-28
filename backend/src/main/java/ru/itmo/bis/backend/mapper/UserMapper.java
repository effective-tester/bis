package ru.itmo.bis.backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.itmo.bis.backend.dto.response.UserDto;
import ru.itmo.bis.backend.model.User;


@Mapper(componentModel = "spring", uses = RoleMapper.class)
public interface UserMapper {

    @Mapping(target = "roles", source = "roles")
    UserDto toDto(User user);
}
