package ru.itmo.bis.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.bis.backend.model.Location;
import ru.itmo.bis.backend.model.User;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface LocationRepository extends JpaRepository<Location, UUID> {

  Optional<Location> findByRegion(String region);
}

