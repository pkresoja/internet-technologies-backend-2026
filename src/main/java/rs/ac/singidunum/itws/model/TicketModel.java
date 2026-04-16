package rs.ac.singidunum.itws.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class TicketModel {
    private Integer airlineId;
    private Integer flightId;
    private String seatingType;
    private Integer count;
}
