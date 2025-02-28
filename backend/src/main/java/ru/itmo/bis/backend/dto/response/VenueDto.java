package ru.itmo.bis.backend.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class VenueDto {

  private UUID id;

  private String name;

  private String venueType;

  private String email;

  private String description;

  private String address;

  private Long capacity;

  private BigDecimal price;

  private LandlordDto landlord;

  private LocationDto location;

  @JsonFormat(
      shape = JsonFormat.Shape.STRING,
      pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'",
      timezone = "UTC"
  )
  private Instant createdAt;

  @JsonFormat(
      shape = JsonFormat.Shape.STRING,
      pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'",
      timezone = "UTC"
  )
  private Instant updatedAt;
}