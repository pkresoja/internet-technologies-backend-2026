package rs.ac.singidunum.itws.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.itws.entity.Airline;
import rs.ac.singidunum.itws.repo.AirlineRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AirlineService {

    private final AirlineRepository repository;

    public List<Airline> getAirlines() {
        return repository.findAllByDeletedAtIsNull();
    }

    public Optional<Airline> getAirlineById(Integer id) {
        return repository.findOneByIdAndDeletedAtIsNull(id);
    }

    public void createAirline(Airline airline) {
        checkAirlineConsistency(airline);
        Airline newAirline = new Airline();
        newAirline.setName(airline.getName());
        newAirline.setCreatedAt(LocalDateTime.now());
        repository.save(newAirline);
    }

    public void updateAirline(Integer id, Airline airline) {
        checkAirlineConsistency(airline);
        Airline existing = repository.findOneByIdAndDeletedAtIsNull(id).orElseThrow();
        existing.setName(airline.getName());
        existing.setUpdatedAt(LocalDateTime.now());
        repository.save(existing);
    }

    public void deleteAirline(Integer id) {
        Airline existing = repository.findOneByIdAndDeletedAtIsNull(id).orElseThrow();
        existing.setDeletedAt(LocalDateTime.now());
        repository.save(existing);
    }

    private void checkAirlineConsistency(Airline airline) {
        if (airline.getName().isEmpty()) {
            throw new RuntimeException("AIRLINE_NAME_CANT_BE_EMPTY");
        }

        if (repository.existsByNameAndDeletedAtIsNull(airline.getName())) {
            throw new RuntimeException("AIRLINE_EXISTS_BY_NAME");
        }
    }
}
