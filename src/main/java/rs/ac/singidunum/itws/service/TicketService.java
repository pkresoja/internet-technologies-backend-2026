package rs.ac.singidunum.itws.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import rs.ac.singidunum.itws.entity.Airline;
import rs.ac.singidunum.itws.entity.Ticket;
import rs.ac.singidunum.itws.entity.User;
import rs.ac.singidunum.itws.model.FlightModel;
import rs.ac.singidunum.itws.model.TicketModel;
import rs.ac.singidunum.itws.repo.TicketRepository;
import rs.ac.singidunum.itws.repo.UserRepository;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepo;
    private final UserRepository userRepo;
    private final FlightService flightService;

    public List<Ticket> getAll() {
        User currentUser = getCurrentUser();

        List<Ticket> tickets = ticketRepo.findByUserAndDeletedAtIsNull(currentUser);

        List<Integer> ids = tickets.stream()
                .map(Ticket::getFlightId)
                .distinct()
                .toList();

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
        User currentUser = getCurrentUser();

        Ticket ticket = new Ticket();

        Airline airline = new Airline();
        airline.setId(model.getAirlineId());
        ticket.setAirline(airline);

        ticket.setFlightId(model.getFlightId());
        ticket.setCount(model.getCount());
        ticket.setUser(currentUser);
        ticket.setSeatingType(model.getSeatingType());
        ticket.setCreatedAt(LocalDateTime.now());

        ticketRepo.save(ticket);
    }

    private User getCurrentUser() {
        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return userRepo.findByUsernameAndIsActiveTrue(username)
                .orElseThrow(() -> new RuntimeException("Current user not found"));
    }
}