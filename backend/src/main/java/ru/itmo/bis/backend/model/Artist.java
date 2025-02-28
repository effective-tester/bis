package ru.itmo.bis.backend.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import ru.itmo.bis.backend.constant.UserRole;

@Entity
@Table(name = "artist")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class Artist extends User {

  @OneToOne(cascade = CascadeType.ALL)
  private User user;

  private String genre;

  @Column(length = 1500)
  private String description;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "location_id")
  private Location location;

  @Override
  public UserRole getRole() {
    return UserRole.ARTIST;
  }
}
