package ru.itmo.bis.backend.mapper;

import org.mapstruct.Mapper;
import ru.itmo.bis.backend.dto.response.LandlordDto;
import ru.itmo.bis.backend.model.Landlord;

@Mapper(componentModel = "spring", uses = {LocationMapper.class})
public interface LandlordMapper {

  LandlordDto toDto(Landlord landlord);
}