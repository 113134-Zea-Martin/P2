package ar.edu.utn.frc.tup.lc.iii.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "score_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScoreDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "score_id")
    private ScoreEntity score;

    @OneToOne
    @JoinColumn(name = "prediction_id")
    private GamePredictionEntity prediction;

    @ManyToOne
    @JoinColumn(name = "result_id")
    private GameResultEntity result;

    private Integer points;
}
