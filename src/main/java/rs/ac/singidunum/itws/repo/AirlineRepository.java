package rs.ac.singidunum.itws.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.singidunum.itws.entity.Airline;

@Repository
public interface AirlineRepository extends JpaRepository<Airline, Integer> {
}
