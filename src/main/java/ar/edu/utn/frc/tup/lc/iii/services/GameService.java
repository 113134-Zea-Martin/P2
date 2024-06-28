package ar.edu.utn.frc.tup.lc.iii.services;

import ar.edu.utn.frc.tup.lc.iii.models.FaseGame;
import ar.edu.utn.frc.tup.lc.iii.models.Game;
import ar.edu.utn.frc.tup.lc.iii.models.GamePrediction;
import ar.edu.utn.frc.tup.lc.iii.models.GameResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GameService {

    List<Game> getAllGamesByFase(FaseGame faseGame);

    Game getGameById(Long id);

    GameResult getGameResultByGameId(Long gameId);

    GameResult setGameResultByGameId(Long gameId, Integer localGolas, Integer visitorGoals);

    List<Game> getUnpredictedGamesByUser(Long userId, FaseGame faseGame);

    GamePrediction predict(Long userId, Long gameId, Integer localGoals, Integer visitorGoals);

    GamePrediction getPrediction(Long userId, Long gameId);

    List<GamePrediction> getPredictionsByUser(Long userId, Boolean includeUnpredicted);

    List<GamePrediction> getPredictionsByUser(Long userId, Boolean includeUnpredicted, FaseGame faseGame);

    List<GamePrediction> getPredictionsByGame(Long gameId);
}
