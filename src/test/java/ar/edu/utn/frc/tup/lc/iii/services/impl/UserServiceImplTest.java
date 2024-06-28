package ar.edu.utn.frc.tup.lc.iii.services.impl;

import ar.edu.utn.frc.tup.lc.iii.entities.UserEntity;
import ar.edu.utn.frc.tup.lc.iii.models.User;
import ar.edu.utn.frc.tup.lc.iii.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.*;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;


import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void testCreateUser_Success() {
        // Arrange
        String email = "test@example.com";
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(email);

        User user = new User();
        user.setEmail(email);

        when(userRepository.findByEmail(email)).thenReturn(null);
        when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);
        when(modelMapper.map(any(UserEntity.class), any())).thenReturn(user);

        // Act
        User result = userService.createUser("John", "Doe", email, "password");

        // Assert

        boolean check = Objects.equals(user.getEmail(), result.getEmail());
        assertTrue(check);

        //verify(userRepository).findByEmail(email);
        //verify(userRepository).save(any(UserEntity.class));
    }

    @Test
    void createUserTest_NULL() {

        MockitoAnnotations.openMocks(this);
        // TODO Testing
        String email = "asd@mail.com";
        User user = new User();
        user.setEmail(email);

        when(userService.getUserByEmail(email)).thenReturn(user);

        HttpClientErrorException errorException = assertThrows(HttpClientErrorException.class, () ->
                userService.createUser("", "", email, ""));


        assertEquals(errorException.getStatusText(), "Email already exists");
        assertEquals(errorException.getStatusCode(), HttpStatus.BAD_REQUEST);

    }

    @Test
    void createUser_whenEmailAlreadyExists_shouldThrowException() {
        String name = "John";
        String lastName = "Doe";
        String email = "johndoe@example.com";
        String password = "password123";

        UserEntity existingUser = new UserEntity();
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(existingUser));

        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class, () -> {
                    userService.createUser(name, lastName, email, password);
                }
        );

        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        assertEquals("Email already exists", exception.getStatusText());
    }

    @Test
    void getUserByEmailTest() {
        // TODO Testing
    }

}