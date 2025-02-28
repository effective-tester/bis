package ru.itmo.bis.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.bis.backend.model.Venue;

import java.util.UUID;

@Repository
public interface VenueRepository extends JpaRepository<Venue, UUID> {
}
