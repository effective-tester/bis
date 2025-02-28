package ru.itmo.bis.backend.dto.request.artist;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.itmo.bis.backend.dto.response.LocationDto;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class UpdateArtistRequest {

  private String email;

  private String name;

  private String genre;

  private String profilePictureUrl;

  private String description;

  private UUID locationId;
}