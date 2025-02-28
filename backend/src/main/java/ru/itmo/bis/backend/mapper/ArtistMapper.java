package ru.itmo.bis.backend.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.itmo.bis.backend.dto.request.artist.UpdateArtistRequest;
import ru.itmo.bis.backend.dto.response.ArtistDto;
import ru.itmo.bis.backend.model.Artist;
import ru.itmo.bis.backend.model.Location;
import ru.itmo.bis.backend.repository.LocationRepository;

import java.util.Optional;
import java.util.UUID;

@Mapper(componentModel = "spring", uses = {LocationMapper.class})
public interface ArtistMapper {

  @Mapping(source = "location", target = "location")
  ArtistDto toDto(Artist artist);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  @Mapping(target = "location", source = "locationId", qualifiedByName = "mapLocation")
  void updateModel(
      UpdateArtistRequest request,
      @MappingTarget Artist artist,
      @Context LocationRepository locationRepository
  );

  @Named("mapLocation")
  default Location mapLocation(UUID locationId, @Context LocationRepository locationRepository) {
    return Optional.ofNullable(locationId)
        .flatMap(locationRepository::findById)
        .orElse(null);
  }
}