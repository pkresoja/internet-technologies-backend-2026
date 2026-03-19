package rs.ac.singidunum.itws.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.singidunum.itws.entity.Airline;

import java.util.List;
import java.util.Optional;

@Repository
public interface AirlineRepository extends JpaRepository<Airline, Integer> {

    List<Airline> findAllByDeletedAtIsNull();

    Optional<Airline> findOneByIdAndDeletedAtIsNull(Integer id);

    Boolean existsByNameAndDeletedAtIsNull(String name);
}
