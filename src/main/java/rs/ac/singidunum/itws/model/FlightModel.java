package rs.ac.singidunum.itws.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FlightModel {
    private Integer id;
    private TypeModel type;
    private String flightKey;
    private String flightNumber;
    private String destination;
    private String scheduledAt;
    private String estimatedAt;
    private String connectedType;
    private String connectedFlight;
    private String plane;
    private String gate;
    private String terminal;
}
