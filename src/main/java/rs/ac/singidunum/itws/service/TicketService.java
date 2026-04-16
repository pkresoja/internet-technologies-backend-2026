package rs.ac.singidunum.itws.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import rs.ac.singidunum.itws.entity.Airline;
import rs.ac.singidunum.itws.entity.Ticket;
import rs.ac.singidunum.itws.model.FlightModel;
import rs.ac.singidunum.itws.model.TicketModel;
import rs.ac.singidunum.itws.repo.TicketRepository;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepo;
    private final FlightService flightService;

    public List<Ticket> getAll() {
        List<Ticket> tickets = ticketRepo.findByUserIdAndDeletedAtIsNull(1);
        List<Integer> ids = tickets.stream().map(Ticket::getFlightId).distinct().toList();
        List<FlightModel> flights = flightService.getFlightsByIds(ids);

        for (Ticket t : tickets) {
            t.setFlight(flights.stream()
                    .filter(f -> f.getId().equals(t.getFlightId()))
                    .findFirst()
                    .orElse(null));
        }
        return tickets;
    }

    public void create(TicketModel model) {
        Ticket ticket = new Ticket();

        Airline airline = new Airline();
        airline.setId(model.getAirlineId());
        ticket.setAirline(airline);

        ticket.setFlightId(model.getFlightId());
        ticket.setCount(model.getCount());
        ticket.setUserId(1);
        ticket.setSeatingType(model.getSeatingType());
        ticket.setCreatedAt(LocalDateTime.now());

        ticketRepo.save(ticket);
    }
}
