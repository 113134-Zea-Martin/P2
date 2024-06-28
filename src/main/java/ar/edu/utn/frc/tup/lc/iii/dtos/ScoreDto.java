package ar.edu.utn.frc.tup.lc.iii.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScoreDto {

    private UserDto user;
    private Integer points;
}
