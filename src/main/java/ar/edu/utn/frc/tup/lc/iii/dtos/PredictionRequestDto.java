package ar.edu.utn.frc.tup.lc.iii.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PredictionRequestDto {

    @PositiveOrZero(message = "local_goals can´t be negative")
    @JsonProperty("local_goals")
    @NotNull(message = "local_goals can´t by null")
    private Integer localGoals;

    @PositiveOrZero(message = "visitor_goals can´t be negative")
    @JsonProperty("visitor_goals")
    @NotNull(message = "visitor_goals can´t by null")
    private Integer visitorGoals;
}
