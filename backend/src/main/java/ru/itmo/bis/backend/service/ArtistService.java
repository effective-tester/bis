package ru.itmo.bis.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itmo.bis.backend.dto.request.artist.UpdateArtistRequest;
import ru.itmo.bis.backend.dto.response.ArtistDto;
import ru.itmo.bis.backend.dto.response.UserDto;
import ru.itmo.bis.backend.exception.api.UserNotFoundException;
import ru.itmo.bis.backend.mapper.ArtistMapper;
import ru.itmo.bis.backend.mapper.UserMapper;
import ru.itmo.bis.backend.model.User;
import ru.itmo.bis.backend.repository.ArtistRepository;
import ru.itmo.bis.backend.repository.LocationRepository;
import ru.itmo.bis.backend.repository.UserRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ArtistService {

    private final ArtistRepository artistRepository;

    private final LocationRepository locationRepository;

    private final ArtistMapper artistMapper;

    @Transactional
    public ArtistDto getArtist(UUID artistId) {
        var artist = artistRepository.findById(artistId)
            .orElseThrow(() -> new UserNotFoundException(artistId));
        return artistMapper.toDto(artist);
    }

    @Transactional
    public ArtistDto updateArtist(UUID artistId, UpdateArtistRequest request) {
        var artist = artistRepository.findById(artistId)
            .orElseThrow(() -> new UserNotFoundException(artistId));
        artistMapper.updateModel(request, artist, locationRepository);
        var updatedArtist = artistRepository.save(artist);
        return artistMapper.toDto(updatedArtist);
    }

    @Transactional
    public void deleteArtist(UUID artistId) {
        var artist = artistRepository.findById(artistId)
            .orElseThrow(() -> new UserNotFoundException(artistId));
        artistRepository.delete(artist);
    }
}
