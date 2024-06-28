package ar.edu.utn.frc.tup.lc.iii.controllers;

import ar.edu.utn.frc.tup.lc.iii.dtos.PredictionRequestDto;
import ar.edu.utn.frc.tup.lc.iii.dtos.PredictionResponseDto;
import ar.edu.utn.frc.tup.lc.iii.models.FaseGame;
import ar.edu.utn.frc.tup.lc.iii.models.Game;
import ar.edu.utn.frc.tup.lc.iii.models.GamePrediction;
import ar.edu.utn.frc.tup.lc.iii.models.GameResult;
import ar.edu.utn.frc.tup.lc.iii.services.GameService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games")
public class GameController {

    @Autowired
    private GameService gameService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Method to get all games.
     *
     * @param faseGame filter by fase game, if not provided, all games are returned.
     * @return all games.
     */
    @GetMapping("")
    public ResponseEntity<List<Game>> getGames(@RequestParam(value = "fase_game", required = false) FaseGame faseGame) {
        List<Game> games = gameService.getAllGamesByFase(faseGame);
        return ResponseEntity.ok(games);
    }

    /**
     * Method to get a game by id.
     *
     * @param id game id
     * @return the game.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Game> getGame(@PathVariable("id") Long id) {
        Game game = gameService.getGameById(id);
        return ResponseEntity.ok(game);
    }

    /**
     * Method to get the result of a game.
     *
     * @param id game id
     * @return the result of the game.
     */
    @GetMapping("/{id}/result")
    public ResponseEntity<GameResult> getGameResult(@PathVariable("id") Long id) {
        GameResult gameResult = gameService.getGameResultByGameId(id);
        return ResponseEntity.ok(gameResult);
    }

    /**
     * Method to set the result of a game. If the result of the game is already set, the result is updated.
     *
     * @param gameId game id
     * @param localGoals local goals
     * @param visitorGoals visitor goals
     * @return the result of the game.
     */
    @PutMapping("/{id}/result")
    public ResponseEntity<GameResult> putGameResult(@PathVariable("id") Long gameId,
                    @RequestParam(value = "local_goals") Integer localGoals,
                    @RequestParam(value = "visitor_goals") Integer visitorGoals){
        GameResult gameResult = gameService.setGameResultByGameId(gameId, localGoals, visitorGoals);
        return ResponseEntity.ok(gameResult);
    }

    /**
     * Method to predict the result of a game, if the user has already predicted the result of the game,
     * the prediction is updated. If the user has not predicted the result of the game, a new prediction is created.
     *
     * @param userId user id
     * @param gameId game id
     * @param predictionRequestDto prediction data
     * @return the prediction of the game.
     */
    @PutMapping("/{id}/predictions")
    public ResponseEntity<PredictionResponseDto> putPrediction(@RequestParam("user_id") Long userId,
                                                               @PathVariable("id") Long gameId,
                                                               @RequestBody @Valid PredictionRequestDto predictionRequestDto) {
        GamePrediction gamePrediction = gameService.predict(userId, gameId, predictionRequestDto.getLocalGoals(), predictionRequestDto.getVisitorGoals());
        return ResponseEntity.ok(modelMapper.map(gamePrediction, PredictionResponseDto.class));
    }

    /**
     * Method to get the prediction of a game.
     *
     * @param userId user id
     * @param gameId game id
     * @return the prediction of the game.
     */
    @GetMapping("/{id}/predictions")
    public ResponseEntity<List<PredictionResponseDto>> getPrediction(
            @RequestParam(value = "user_id") Long userId,
            @PathVariable("id") Long gameId) {

        GamePrediction gamePrediction = gameService.getPrediction(userId, gameId);
        return ResponseEntity.ok(List.of(modelMapper.map(gamePrediction, PredictionResponseDto.class)));
    }

    /**
     * Method to get the prediction of a game.
     *
     * @param userId user id
     * @param includeUnpredicted can be true or false, if true, the method returns the predictions
     *                           of the games that the user has not predicted to.
     * @return the prediction of the game.
     */
    @GetMapping("/predictions")
    public ResponseEntity<List<PredictionResponseDto>> getPrediction(
            @RequestParam(value = "user_id") Long userId,
            @RequestParam(value = "include_unpredicted") Boolean includeUnpredicted,
            @RequestParam(value = "fase_game", required = false) FaseGame faseGame) {

        List<GamePrediction> gamePredictions;
        if(faseGame != null) {
            gamePredictions = gameService.getPredictionsByUser(userId, includeUnpredicted, faseGame);
        } else {
            gamePredictions = gameService.getPredictionsByUser(userId, includeUnpredicted);
        }
        return ResponseEntity.ok(modelMapper.map(gamePredictions, new TypeToken<List<PredictionResponseDto>>() {}.getType()));
    }
}
