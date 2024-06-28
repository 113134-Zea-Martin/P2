package ar.edu.utn.frc.tup.lc.iii.services.impl;

import ar.edu.utn.frc.tup.lc.iii.entities.GameEntity;
import ar.edu.utn.frc.tup.lc.iii.entities.GamePredictionEntity;
import ar.edu.utn.frc.tup.lc.iii.entities.GameResultEntity;
import ar.edu.utn.frc.tup.lc.iii.entities.UserEntity;
import ar.edu.utn.frc.tup.lc.iii.models.*;
import ar.edu.utn.frc.tup.lc.iii.repositories.GamePredictionRepository;
import ar.edu.utn.frc.tup.lc.iii.repositories.GameRepository;
import ar.edu.utn.frc.tup.lc.iii.repositories.GameResultRepository;
import ar.edu.utn.frc.tup.lc.iii.repositories.UserRepository;
import ar.edu.utn.frc.tup.lc.iii.services.GameService;
import ar.edu.utn.frc.tup.lc.iii.services.ScoreService;
import ar.edu.utn.frc.tup.lc.iii.services.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private GameResultRepository gameResultRepository;

    @Autowired
    private GamePredictionRepository gamePredictionRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ScoreService scoreService;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;

    /**
     * Gets all the games by fase.
     *
     * @return the list of games
     */
    @Override
    public List<Game> getAllGamesByFase(FaseGame faseGame) {
        if (faseGame == null) {
            List<GameEntity> gameEntities = gameRepository.findAll();
            return modelMapper.map(gameEntities, new TypeToken<List<Game>>() {
            }.getType());
        } else {
            List<GameEntity> gameEntities = gameRepository.findAllByFaseGame(faseGame);
            return modelMapper.map(gameEntities, new TypeToken<List<Game>>() {
            }.getType());
        }
    }

    /**
     * Gets a game by id.
     *
     * @param id the game id
     * @return the game if exists, null otherwise
     */
    @Override
    public Game getGameById(Long id) {
        Optional<GameEntity> gameOptional = gameRepository.findById(id);
        return gameOptional.map(game -> modelMapper.map(game, Game.class)).orElse(null);
    }

    /**
     * Gets the result of a game by game id.
     *
     * @param gameId the game id
     * @return the result of the game.
     */
    @Override
    public GameResult getGameResultByGameId(Long gameId) {
        GameResultEntity gameResultEntity = gameResultRepository.findByGameId(gameId);
        if (gameResultEntity == null) {
            Game game = getGameById(gameId);
            GameResult gameResult = new GameResult();
            gameResult.setGame(game);
            return gameResult;
        } else {
            return modelMapper.map(gameResultEntity, GameResult.class);
        }
    }

    /**
     * Sets the result of a game. This method creates the result if it does not exist, otherwise it updates the result.
     *
     * @param gameId       the game id
     * @param localGolas   the local goals
     * @param visitorGoals the visitor goals
     * @return the result of the game
     */
    @Override
    public GameResult setGameResultByGameId(Long gameId, Integer localGolas, Integer visitorGoals) {
        // TODO 5 - Registro de resultados partidos: El sistema deberá permitir a un administrador ingresar los resultados reales de
        //   los partidos de la fase de grupos de la Copa América 2024. Adicionalmente debe permitir modificar los resultados de
        //   los resultados de los partidos en caso de ser necesario.

        // 1. Get the result of the game by game id

        // 2. If the result does not exist, create a new GameResult with the given data
        // 2.a. Get the game by id and check if it not exists throw a HttpClientErrorException
        // with status code 400 and message "Game does not exist"
        // 2.b. if the game exists, create a new GameResult with the given data and save it
        // 3. If the result exists, update the result with the given data and save it
        // 4. Calculate the score of the predictions of the game calling the method this.calculateScore(gameResult)
        // 5. Return the result
        return null;
    }

    /**
     * Gets the games that the user has not predicted to.
     *
     * @param userId   the user id
     * @param faseGame the fase of the championship
     * @return the list of unpredicted games
     */
    @Override
    public List<Game> getUnpredictedGamesByUser(Long userId, FaseGame faseGame) {
        List<GameEntity> gameEntities = gameRepository.findAllGamesNotPredictedByUser(userId, faseGame);
        return modelMapper.map(gameEntities, new TypeToken<List<Game>>() {
        }.getType());
    }

    /**
     * Predicts the result of a game. If the prediction already exists, it updates the prediction.
     * If the user or game does not exist, it "throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,
     * "User or Game do not exist");"
     *
     * @param userId       user id
     * @param gameId       game id
     * @param localGoals   local goals predicted
     * @param visitorGoals visitor goals predicted
     * @return The prediction of the game
     */
    @Override
    public GamePrediction predict(Long userId, Long gameId, Integer localGoals, Integer visitorGoals) {
        // TODO 1 - Carga de pronósticos: El sistema debe permitir a los jugadores ingresar sus pronósticos.DONE
        //  para los partidos de la fase de grupos de la Copa América 2024. Para ello, deberá solicitar
        //  al usuario que ingrese el resultado de cada partido indicando los goles que se darán por cada equipo.
        //  Los pronósticos deberán ser ingresados antes del inicio de cada partido. El sistema deberá validar
        //  que los pronósticos ingresados sean válidos y que no se ingresen pronósticos para partidos que ya
        //  se hayan iniciado y que la cantidad de goles ingresados sea un entero positivo o cero.

        GamePredictionEntity gamePredictionEntity;

        // 1. Get the user by id and check if it exists
        Optional<UserEntity> userEntity = userRepository.findById(userId);
        // 1.a If the user does not exist, throw a HttpClientErrorException with status code 400 and message "User does not exist"
        if (userEntity.isEmpty()) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "User does not exist");
        }
        User user = modelMapper.map(userEntity.get(), User.class);

        // 2. Get the game by id and check if it exists
        Optional<GameEntity> game = gameRepository.findById(gameId);
        // 2.a If the game does not exist, throw a HttpClientErrorException with status code 400 and message "Game does not exist"
        if (game.isEmpty()) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Game does not exist");
        }
        Game gameMapped = modelMapper.map(game,Game.class);
        // 2.b If the game has already started, throw a HttpClientErrorException with status code 400 and message "The game has already started"
        if (gameMapped.getDate().isBefore(LocalDateTime.now())) {
            throw new HttpClientErrorException(HttpStatusCode.valueOf(400), "The game has already started");
        }
        // 3. Get the prediction of the user for the game
        GamePrediction gamePrediction = getPrediction(userId, gameId);
        // 4. If the prediction does not exist, create a new GamePrediction with the given data
        if (gamePrediction == null) {
            GamePrediction newGamePrediction = new GamePrediction();
            newGamePrediction.setUser(user);
            newGamePrediction.setGame(gameMapped);
            newGamePrediction.setLocalGoals(localGoals);
            newGamePrediction.setVisitorGoals(visitorGoals);
            newGamePrediction.setPredictionDate(LocalDateTime.now());
            if (localGoals > visitorGoals) {
                newGamePrediction.setResult(Result.LOCAL_WIN);
            } else if (localGoals < visitorGoals) {
                newGamePrediction.setResult(Result.VISITOR_WIN);
            } else {
                newGamePrediction.setResult(Result.TIE);
            }
            gamePredictionEntity = modelMapper.map(newGamePrediction, GamePredictionEntity.class);
            // 5. If the prediction exists, update the prediction with the given data
        } else {
            gamePrediction.setLocalGoals(localGoals);
            gamePrediction.setVisitorGoals(visitorGoals);
            gamePrediction.setPredictionDate(LocalDateTime.now());
            if (localGoals > visitorGoals) {
                gamePrediction.setResult(Result.LOCAL_WIN);
            } else if (localGoals < visitorGoals) {
                gamePrediction.setResult(Result.VISITOR_WIN);
            } else {
                gamePrediction.setResult(Result.TIE);
            }
            gamePredictionEntity = modelMapper.map(gamePrediction, GamePredictionEntity.class);
        }
        // 6. Save the prediction
        gamePredictionRepository.save(gamePredictionEntity);
        // 7. Return the prediction

        return gamePrediction;
    }

    /**
     * Gets the prediction of a user for a game
     *
     * @param userId the user id
     * @param gameId the game id
     * @return the prediction of the game if exists, null otherwise
     */
    @Override
    public GamePrediction getPrediction(Long userId, Long gameId) {
        GamePredictionEntity gamePredictionEntity = gamePredictionRepository.findByUserIdAndGameId(userId, gameId).orElse(null);
        return gamePredictionEntity != null ? modelMapper.map(gamePredictionEntity, GamePrediction.class) : null;
    }

    /**
     * Gets the predictions of a user.
     *
     * @param userId             the user id
     * @param includeUnpredicted if true, the method returns the predictions of the games that the user has not predicted to.
     * @return the predictions of the user
     */
    @Override
    public List<GamePrediction> getPredictionsByUser(Long userId, Boolean includeUnpredicted) {
        if (includeUnpredicted) {
            return getPredictionsByUserWithUnpredictedGames(userId);
        } else {
            List<GamePredictionEntity> gamePredictionEntities = gamePredictionRepository.findByUserId(userId);
            return modelMapper.map(gamePredictionEntities, new TypeToken<List<GamePrediction>>() {
            }.getType());
        }
    }

    /**
     * Gets the predictions of a user for a specific fase.
     *
     * @param userId             the user id
     * @param includeUnpredicted if true, the method returns the predictions of the games that the user has not predicted to.
     * @param faseGame           the fase of the championship
     * @return the predictions of the user
     */
    @Override
    public List<GamePrediction> getPredictionsByUser(Long userId, Boolean includeUnpredicted, FaseGame faseGame) {
        if (includeUnpredicted) {
            return getPredictionsByUserWithUnpredictedGames(userId, faseGame);
        } else {
            List<GamePredictionEntity> gamePredictionEntities = gamePredictionRepository.findByUserId(userId);
            return modelMapper.map(gamePredictionEntities, new TypeToken<List<GamePrediction>>() {
            }.getType());
        }
    }

    /**
     * Gets the predictions of a specific game.
     *
     * @param gameId the game id
     * @return the predictions of the game
     */
    @Override
    public List<GamePrediction> getPredictionsByGame(Long gameId) {
        List<GamePredictionEntity> gamePredictionEntities = gamePredictionRepository.findByGameId(gameId);
        return modelMapper.map(gamePredictionEntities, new TypeToken<List<GamePrediction>>() {
        }.getType());
    }

    /**
     * Calculates the result of a game.
     * TIE if localGoals == visitorGoals
     * LOCAL_WIN if localGoals > visitorGoals
     * VISITOR_WIN if localGoals < visitorGoals
     *
     * @param localGolas   the local goals
     * @param visitorGoals the visitor goals
     * @return the result of the game
     */
    private Result calculateResult(Integer localGolas, Integer visitorGoals) {
        if (localGolas.equals(visitorGoals)) {
            return Result.TIE;
        } else if (localGolas > visitorGoals) {
            return Result.LOCAL_WIN;
        } else {
            return Result.VISITOR_WIN;
        }
    }

    /**
     * Gets the predictions of a user with the games that the user has not predicted to, only for the group stage.
     *
     * @param userId the user id
     * @return the predictions of the user with the games that the user has not predicted to
     */
    private List<GamePrediction> getPredictionsByUserWithUnpredictedGames(Long userId) {
        return getPredictionsByUserWithUnpredictedGames(userId, FaseGame.GROUP_STAGE);
    }

    /**
     * Gets the predictions of a user with the games that the user has not predicted to for a specific fase.
     *
     * @param userId the user id
     * @return the predictions of the user with the games that the user has not predicted to
     */
    private List<GamePrediction> getPredictionsByUserWithUnpredictedGames(Long userId, FaseGame faseGame) {
        User user = userService.getUserById(userId);
        List<GamePredictionEntity> gamePredictionEntities = gamePredictionRepository.findByUserId(userId);
        List<GamePrediction> gamePredictions = modelMapper.map(gamePredictionEntities, new TypeToken<List<GamePrediction>>() {
        }.getType());
        List<Game> unpredictedGames = this.getUnpredictedGamesByUser(userId, faseGame);
        for (Game game : unpredictedGames) {
            GamePrediction gamePrediction = new GamePrediction();
            gamePrediction.setGame(game);
            gamePrediction.setUser(user);
            gamePredictions.add(gamePrediction);
        }
        return gamePredictions;
    }

    /**
     * Calculates the score of the predictions of a game given the result of the game.
     *
     * @param gameResult the result of the game
     */
    private void calculateScore(GameResult gameResult) {
        // TODO 2 - Cálculo de puntajes: El sistema deberá calcular los puntajes de los usuarios en base a los resultados reales de
        //   los partidos. Para ello, deberá comparar los pronósticos de los usuarios con los resultados reales y asignar puntos
        //   según la precisión de las predicciones. Los puntajes se calcularán cada vez que el administrador cargue o modifique
        //   el resultado de un partido. Los puntajes se asignarán de la siguiente manera:
        //   - 1 punto por acertar el resultado de un partido.
        //   - 3 puntos adicionales por acertar el resultado exacto de un partido.

        // 1. Get the predictions of the game with the method this.getPredictionsByGame(gameResult.getGame().getId())
        List<GamePrediction> lGamePrediction = this.getPredictionsByGame(gameResult.getGame().getId());
        // 2. For each prediction, calculate the score with the method scoreService.calculateScore(gamePrediction, gameResult)
        for (GamePrediction gamePrediction : lGamePrediction) {
            scoreService.calculateScore(gamePrediction, gameResult);
        }
    }

}
