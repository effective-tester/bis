package ru.itmo.bis.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.bis.backend.model.Booking;

import java.util.UUID;

@Repository
public interface BookingRepository extends JpaRepository<Booking, UUID> {
}
