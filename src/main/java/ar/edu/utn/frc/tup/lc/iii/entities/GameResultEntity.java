package ar.edu.utn.frc.tup.lc.iii.entities;


import ar.edu.utn.frc.tup.lc.iii.models.Result;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "game_results")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameResultEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "game_id", unique = true, nullable = false)
    private GameEntity game;

    private Integer localGoals;

    private Integer visitorGoals;

    @Enumerated(EnumType.STRING)
    private Result result;
}
