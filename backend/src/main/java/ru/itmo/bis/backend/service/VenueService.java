package ru.itmo.bis.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itmo.bis.backend.dto.response.VenueDto;
import ru.itmo.bis.backend.exception.api.VenueNotFoundException;
import ru.itmo.bis.backend.mapper.VenueMapper;
import ru.itmo.bis.backend.repository.VenueRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VenueService {

  private final VenueRepository venueRepository;

  private final VenueMapper venueMapper;

  @Transactional
  public VenueDto getVenue(UUID venueId) {
    var venue = venueRepository.findById(venueId)
        .orElseThrow(() -> new VenueNotFoundException(venueId));
    return venueMapper.toDto(venue);
  }

  @Transactional
  public void deleteVenue(UUID venueId) {
    var venue = venueRepository.findById(venueId)
        .orElseThrow(() -> new VenueNotFoundException(venueId));
    venueRepository.delete(venue);
  }
}
