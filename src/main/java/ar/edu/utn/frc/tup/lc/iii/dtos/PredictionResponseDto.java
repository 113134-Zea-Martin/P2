package ar.edu.utn.frc.tup.lc.iii.dtos;

import ar.edu.utn.frc.tup.lc.iii.models.Game;
import ar.edu.utn.frc.tup.lc.iii.models.Result;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PredictionResponseDto {

    private Long id;
    private UserDto user;
    private Game game;

    @JsonProperty("local_goals")
    private Integer localGoals;

    @JsonProperty("visitor_goals")
    private Integer visitorGoals;
    private Result result;
}
