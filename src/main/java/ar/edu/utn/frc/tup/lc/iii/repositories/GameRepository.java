package ar.edu.utn.frc.tup.lc.iii.repositories;

import ar.edu.utn.frc.tup.lc.iii.entities.GameEntity;
import ar.edu.utn.frc.tup.lc.iii.models.FaseGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<GameEntity, Long> {

    @Query("SELECT g FROM GameEntity g WHERE g.faseGame = :faseGame AND g.id NOT IN (" +
            "SELECT gp.game.id FROM GamePredictionEntity gp WHERE gp.user.id = :userId)")
    List<GameEntity> findAllGamesNotPredictedByUser(Long userId, FaseGame faseGame);

    List<GameEntity> findAllByFaseGame(FaseGame faseGame);
}
