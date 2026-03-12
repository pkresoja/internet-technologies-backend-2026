package rs.ac.singidunum.itws.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.singidunum.itws.entity.Airline;
import rs.ac.singidunum.itws.repo.AirlineRepository;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/airline")
public class AirlineController {

    private final AirlineRepository repository;

    @GetMapping
    public List<Airline> getAirlines() {
        return repository.findAll();
    }
}
