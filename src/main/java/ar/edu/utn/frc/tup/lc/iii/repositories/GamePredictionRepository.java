package ar.edu.utn.frc.tup.lc.iii.repositories;

import ar.edu.utn.frc.tup.lc.iii.entities.GamePredictionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GamePredictionRepository extends JpaRepository<GamePredictionEntity, Long> {

    /**
     * Method to get the prediction of a game by user id and game id.
     * @param userId user id
     * @param gameId game id
     * @return the prediction of the game.
     */
    Optional<GamePredictionEntity> findByUserIdAndGameId(Long userId, Long gameId);

    /**
     * Method to get the predictions of a user.
     * @param userId user id
     * @return the predictions of the user.
     */
    List<GamePredictionEntity> findByUserId(Long userId);

    /**
     * Method to get the predictions of a game.
     * @param gameId game id
     *
     * @return the predictions of the game.
     */
    List<GamePredictionEntity> findByGameId(Long gameId);

}
