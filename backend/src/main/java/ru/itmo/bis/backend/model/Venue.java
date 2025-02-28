package ru.itmo.bis.backend.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "venue")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Venue {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  private String name;

  @Column(length = 1500)
  private String description;

  private String venueType;

  private double price;

  @ManyToOne
  @JoinColumn(name = "location_id")
  private Location location;

  private String address;

  private int capacity;

  private String email;

  @ManyToOne
  @JoinColumn(name = "landlord_id", nullable = false)
  private Landlord landlord;

  @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinTable(
      name = "venue_equipment",
      joinColumns = @JoinColumn(name = "venue_id"),
      inverseJoinColumns = @JoinColumn(name = "equipment_id")
  )
  private Set<Equipment> equipment = new HashSet<>();

  @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinTable(
      name = "venue_additional_service",
      joinColumns = @JoinColumn(name = "venue_id"),
      inverseJoinColumns = @JoinColumn(name = "additional_service_id")
  )
  private Set<AdditionalService> additionalServices = new HashSet<>();

  @OneToMany(mappedBy = "venue", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Review> reviews = new HashSet<>();

  @OneToMany(mappedBy = "venue", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Booking> bookings = new HashSet<>();

  @Column(name = "created_at", nullable = false, updatable = false)
  private Instant createdAt;

  @Column(name = "updated_at", nullable = false)
  private Instant updatedAt;

  @PrePersist
  public void onCreate() {
    Instant now = Instant.now();
    this.createdAt = now;
    this.updatedAt = now;
  }

  @PreUpdate
  public void onUpdate() {
    this.updatedAt = Instant.now();
  }
}

