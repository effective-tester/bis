package ru.itmo.bis.backend.mapper;

import org.mapstruct.Mapper;
import ru.itmo.bis.backend.dto.response.RoleDto;
import ru.itmo.bis.backend.model.Role;


@Mapper(componentModel = "spring")
public interface RoleMapper {

  RoleDto toDto(Role role);
}
