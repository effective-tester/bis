package ru.itmo.bis.backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.itmo.bis.backend.dto.response.VenueDto;
import ru.itmo.bis.backend.model.Venue;

@Mapper(componentModel = "spring", uses = {LocationMapper.class, LandlordMapper.class})
public interface VenueMapper {

  @Mapping(source = "landlord", target = "landlord")
  @Mapping(source = "location", target = "location")
  VenueDto toDto(Venue artist);
}