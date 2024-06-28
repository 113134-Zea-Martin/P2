package ar.edu.utn.frc.tup.lc.iii.services.impl;

import ar.edu.utn.frc.tup.lc.iii.entities.GameEntity;
import ar.edu.utn.frc.tup.lc.iii.entities.UserEntity;
import ar.edu.utn.frc.tup.lc.iii.models.Game;
import ar.edu.utn.frc.tup.lc.iii.models.GamePrediction;
import ar.edu.utn.frc.tup.lc.iii.models.User;
import ar.edu.utn.frc.tup.lc.iii.repositories.GamePredictionRepository;
import ar.edu.utn.frc.tup.lc.iii.repositories.GameRepository;
import ar.edu.utn.frc.tup.lc.iii.repositories.UserRepository;
import ar.edu.utn.frc.tup.lc.iii.services.GameService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class GameServiceImplTest {


    @Mock
    private UserRepository userRepository;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private GamePredictionRepository gamePredictionRepository;
    @Mock
    private GameRepository gameRepository;

    @InjectMocks
    private GameServiceImpl gameServiceImpl;


    @Test
    void setGameResultByGameIdTest() {
        // TODO Testing
    }

    @Test
    void predictTest_UserEmpty() {
        // TODO Testing
        Long userId = 1L;
        Long gameId = 1L;
        Integer localGoals = 3;
        Integer visitorGoals = 1;

        UserEntity userEntity = new UserEntity();

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        HttpClientErrorException errorException = assertThrows(HttpClientErrorException.class, () -> {
            gameServiceImpl.predict(userId, gameId, localGoals, visitorGoals);
        });

        assertEquals(errorException.getStatusCode(), HttpStatusCode.valueOf(404));
        assertEquals(errorException.getStatusText(), "User does not exist");

    }

    @Test
    void predictTest_GameNull() {
        Long userId = 1L;
        Long gameId = 1L;
        Integer localGoals = 3;
        Integer visitorGoals = 1;

        UserEntity userEntity = new UserEntity();

        when(userRepository.findById(userId)).thenReturn(Optional.of(userEntity));

        Game game = null;
        when(gameRepository.findById(gameId)).thenReturn(Optional.empty());

        HttpClientErrorException errorException = assertThrows(HttpClientErrorException.class, () -> {
            gameServiceImpl.predict(userId, gameId, localGoals, visitorGoals);
        });

        assertEquals(errorException.getStatusCode(), HttpStatusCode.valueOf(404));
        assertEquals(errorException.getStatusText(), "Game does not exist");
    }

    @Test
    void predictTest_GamePassed() {
        Long userId = 1L;
        Long gameId = 1L;
        Integer localGoals = 3;
        Integer visitorGoals = 1;

        UserEntity userEntity = new UserEntity();
        when(userRepository.findById(userId)).thenReturn(Optional.of(userEntity));

        GameEntity gameEntity = new GameEntity();
        when(gameRepository.findById(gameId)).thenReturn(Optional.of(gameEntity));

        Game game = modelMapper.map(gameEntity,Game.class);

        when(game.getDate().isBefore(LocalDateTime.now())).thenReturn(true);

        HttpClientErrorException error = assertThrows(HttpClientErrorException.class,() -> {
            gameServiceImpl.predict(userId, gameId, localGoals, visitorGoals);
        });

        assertEquals(error.getStatusCode(), HttpStatusCode.valueOf(400));
        assertEquals(error.getStatusText(), "The game has already started");

    }


}