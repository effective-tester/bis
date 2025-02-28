package ru.itmo.bis.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itmo.bis.backend.dto.response.VenueDto;
import ru.itmo.bis.backend.service.VenueService;

import java.util.UUID;


@RestController
@RequestMapping("/landlords/{landlordId}/venues")
@RequiredArgsConstructor
public class VenueController {

  private final VenueService venueService;

  @GetMapping("/{venueId}")
  public ResponseEntity<VenueDto> get(@PathVariable UUID venueId) {
    var venue = venueService.getVenue(venueId);
    return ResponseEntity.ok(venue);
  }

  @DeleteMapping("/{venueId}")
  public ResponseEntity<VenueDto> delete(@PathVariable UUID venueId) {
    venueService.deleteVenue(venueId);
    return ResponseEntity.noContent().build();
  }
}
