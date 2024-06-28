package ar.edu.utn.frc.tup.lc.iii.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDto {

    @NotNull(message = "name can´t by null")
    private String name;

    @NotNull(message = "last name can´t by null")
    @JsonProperty("last_name")
    private String lastName;

    @NotNull(message = "email can´t by null")
    private String email;

    @NotNull(message = "password can´t by null")
    private String password;
}
