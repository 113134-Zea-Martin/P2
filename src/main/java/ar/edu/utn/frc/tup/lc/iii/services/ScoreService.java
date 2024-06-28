package ar.edu.utn.frc.tup.lc.iii.services;

import ar.edu.utn.frc.tup.lc.iii.models.GamePrediction;
import ar.edu.utn.frc.tup.lc.iii.models.GameResult;
import ar.edu.utn.frc.tup.lc.iii.models.Score;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ScoreService {

    void calculateScore(GamePrediction gamePrediction, GameResult gameResult);

    List<Score> getScores();
}
