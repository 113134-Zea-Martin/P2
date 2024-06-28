package ar.edu.utn.frc.tup.lc.iii.entities;

import ar.edu.utn.frc.tup.lc.iii.models.FaseGame;
import ar.edu.utn.frc.tup.lc.iii.models.Group;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "games")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "local_team_id")
    private TeamEntity local;

    @ManyToOne
    @JoinColumn(name = "visitor_team_id")
    private TeamEntity visitor;

    private String stadium;

    private LocalDateTime gameDate;

    @Enumerated(EnumType.STRING)
    private FaseGame faseGame;

    @Enumerated(EnumType.STRING)
    @Column()
    private Group groupStage;
}
