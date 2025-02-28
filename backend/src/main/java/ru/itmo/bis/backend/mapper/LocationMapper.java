package ru.itmo.bis.backend.mapper;

import org.mapstruct.Mapper;
import ru.itmo.bis.backend.dto.response.LocationDto;
import ru.itmo.bis.backend.model.Location;

@Mapper(componentModel = "spring")
public interface LocationMapper {

  LocationDto toDto(Location location);

  Location toModel(LocationDto locationDto);
}
