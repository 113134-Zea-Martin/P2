package ar.edu.utn.frc.tup.lc.iii.services.impl;

import ar.edu.utn.frc.tup.lc.iii.entities.*;
import ar.edu.utn.frc.tup.lc.iii.models.GamePrediction;
import ar.edu.utn.frc.tup.lc.iii.models.GameResult;
import ar.edu.utn.frc.tup.lc.iii.models.Score;
import ar.edu.utn.frc.tup.lc.iii.repositories.ScoreDetailRepository;
import ar.edu.utn.frc.tup.lc.iii.repositories.ScoreRepository;
import ar.edu.utn.frc.tup.lc.iii.services.ScoreService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    private ScoreRepository scoreRepository;

    @Autowired
    private ScoreDetailRepository scoreDetailRepository;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Calculate the score of a user based on the prediction and the result of a game.
     * If the user guessed the result of the game, he gets 1 point.
     * If the user guessed the result and the goals of the local and visitor teams, he gets 3 points.
     * If the user didn't guess the result of the game, he gets 0 points.
     *
     * @param gamePrediction the prediction of the user.
     * @param gameResult     the result of the game.
     */
    @Transactional
    @Override
    public void calculateScore(GamePrediction gamePrediction, GameResult gameResult) {
        // TODO 2 - Cálculo de puntajes: Esta pare del flujo calcula para cada predicción su puntaje.
        //  Además deja un registro del detalle del puntaje en la tabla score_detail.
        GamePredictionEntity gamePredictionEntity = modelMapper.map(gamePrediction, GamePredictionEntity.class);
        GameResultEntity gameResultEntity = modelMapper.map(gameResult, GameResultEntity.class);

        // 1. Determine the points of the user based on the prediction and the result of the game.
        // 1.a. Initialize a variable points with 0.
        Integer points = 0;
        // 1.b. If the result of the prediction is equal to the result of the game, the user gets 1 point.
        if (gamePrediction.getResult() == gameResult.getResult()) {
            points++;
            // 1.c. If the user guessed the result and the goals of the local and visitor teams, he gets 3 extra points.
            if (Objects.equals(gamePrediction.getLocalGoals(), gameResult.getLocalGoals())
                    && Objects.equals(gamePrediction.getVisitorGoals(), gameResult.getVisitorGoals())) {
                points = points + 3;
            }
        }
        // 2. Get the score of the user by user id.
        Optional<ScoreEntity> scoreEntity = scoreRepository.findByUserId(gamePrediction.getUser().getId());
        // 3. If the user has a score, get the score detail of the user by score id and prediction game id.
        if (scoreEntity.isPresent()) {
            Optional<ScoreDetailEntity> scoreDetailEntity =
                    scoreDetailRepository.findByScoreIdAndPredictionGameId(scoreEntity.get().getId(), gamePrediction.getId());
            // 3.a. If the user has a score detail, reduce the points of the user score by the points of the score detail.
            if (scoreDetailEntity.isPresent()) {
                ScoreDetailEntity scoreDetail = scoreDetailEntity.get();
                ScoreEntity score = scoreEntity.get();
                score.setPoints(score.getPoints() - scoreDetail.getPoints());
                //Set the new points of the score detail with the points calculated in step 1 and save the score detail.
                scoreDetail.setPoints(points);
                scoreDetailRepository.save(scoreDetail);
            } else {
                // 3.b. If the user has not a score detail, create a new score detail with the points calculated in step 1 and save it.
                ScoreDetailEntity scoreDetail = new ScoreDetailEntity();
                scoreDetail.setScore(scoreEntity.get());
                scoreDetail.setPoints(points);
                scoreDetailRepository.save(scoreDetail);
            }
        } else {
            // 4. If the user has not a score, create a new score with the user, the points calculated in step 1 and save it.
            UserEntity userEntity = modelMapper.map(gamePrediction.getUser(), UserEntity.class);
            ScoreEntity scoreEntity1 = new ScoreEntity();
            scoreEntity1.setUser(userEntity);
            scoreEntity1.setPoints(points);
            scoreRepository.save(scoreEntity1);

            // 4.a. Create the score detail with the score, the prediction, the result and the points calculated in step 1 and save it.
            ScoreDetailEntity scoreDetailEntity = new ScoreDetailEntity();
            scoreDetailEntity.setScore(scoreEntity1);
            scoreDetailEntity.setPoints(points);
            scoreDetailEntity.setPrediction(gamePredictionEntity);
            scoreDetailEntity.setResult(gameResultEntity);
            scoreDetailRepository.save(scoreDetailEntity);
        }
    }

    /**
     * Get the scores of all users ordered by points, user last name and name.
     *
     * @return the scores of all users.
     */
    @Override
    public List<Score> getScores() {
        List<ScoreEntity> scoreEntities = scoreRepository.findAllOrdered();
        return modelMapper.map(scoreEntities, new TypeToken<List<Score>>() {
        }.getType());
    }
}
