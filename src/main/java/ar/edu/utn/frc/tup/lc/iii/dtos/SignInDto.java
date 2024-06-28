package ar.edu.utn.frc.tup.lc.iii.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInDto {

    @Email
    @NotNull(message = "email can´t by null")
    private String email;

    @NotNull(message = "password can´t by null")
    private String password;
}
