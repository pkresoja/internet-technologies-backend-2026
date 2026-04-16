package rs.ac.singidunum.itws.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import rs.ac.singidunum.itws.entity.Ticket;
import rs.ac.singidunum.itws.model.TicketModel;
import rs.ac.singidunum.itws.service.TicketService;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(path = "/api/ticket")
public class TicketController {
    private final TicketService service;

    @GetMapping
    public List<Ticket> getTickets() {
        return service.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createTicket(@RequestBody TicketModel model) {
        service.create(model);
    }
}
