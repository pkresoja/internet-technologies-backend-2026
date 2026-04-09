package rs.ac.singidunum.itws.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import rs.ac.singidunum.itws.model.FlightModel;
import rs.ac.singidunum.itws.service.FlightService;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(path = "/api/flight")
public class FlightController {
    private final FlightService service;

    @GetMapping
    public List<FlightModel> getAll() {
        return service.getFlights();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<FlightModel> getById(@PathVariable Integer id) {
        return ResponseEntity.of(service.getFlightById(id));
    }

}
