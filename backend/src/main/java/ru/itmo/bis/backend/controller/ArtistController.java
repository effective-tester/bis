package ru.itmo.bis.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itmo.bis.backend.dto.request.artist.UpdateArtistRequest;
import ru.itmo.bis.backend.dto.response.ArtistDto;
import ru.itmo.bis.backend.service.ArtistService;

import java.util.UUID;


@RestController
@RequestMapping("/artists/{artistId}")
@RequiredArgsConstructor
public class ArtistController {

  private final ArtistService artistService;

  @GetMapping
  public ResponseEntity<ArtistDto> get(@PathVariable UUID artistId) {
    var artist = artistService.getArtist(artistId);
    return ResponseEntity.ok(artist);
  }

  @PutMapping
  public ResponseEntity<ArtistDto> update(
      @PathVariable UUID artistId,
      @RequestBody UpdateArtistRequest request
  ) {
    var artist = artistService.updateArtist(artistId, request);
    return ResponseEntity.ok(artist);
  }

  @DeleteMapping
  public ResponseEntity<ArtistDto> delete(@PathVariable UUID artistId) {
    artistService.deleteArtist(artistId);
    return ResponseEntity.noContent().build();
  }
}
