package rs.ac.singidunum.itws.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.ac.singidunum.itws.entity.Ticket;
import java.util.List;


@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    List<Ticket> findByUserIdAndDeletedAtIsNull(Integer userId);
}
