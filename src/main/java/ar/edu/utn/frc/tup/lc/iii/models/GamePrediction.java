package ar.edu.utn.frc.tup.lc.iii.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GamePrediction {

    private Long id;
    private User user;
    private Game game;
    private Integer localGoals;
    private Integer visitorGoals;
    private LocalDateTime predictionDate;
    private Result result;

}
