package rs.ac.singidunum.itws.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TokenModel {
    private String access;
    private String refresh;
    private String username;
}
