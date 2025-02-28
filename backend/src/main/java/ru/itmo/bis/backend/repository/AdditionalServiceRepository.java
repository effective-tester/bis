package ru.itmo.bis.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.bis.backend.model.AdditionalService;

import java.util.UUID;

@Repository
public interface AdditionalServiceRepository extends JpaRepository<AdditionalService, UUID> {
}
