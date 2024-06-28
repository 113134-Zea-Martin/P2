package ar.edu.utn.frc.tup.lc.iii.services.impl;

import ar.edu.utn.frc.tup.lc.iii.entities.UserEntity;
import ar.edu.utn.frc.tup.lc.iii.models.User;
import ar.edu.utn.frc.tup.lc.iii.repositories.UserRepository;
import ar.edu.utn.frc.tup.lc.iii.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Creates a new user with the given data
     * Under the hood, it´s checking if the email is already in use, and if not, it creates the user.
     *
     * @param name     name of the user
     * @param lastName last name of the user
     * @param email    email of the user (unique)
     * @param password password of the user
     * @return The created user or "throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Email already exists")"
     * if the email is already in use
     */
    @Override
    public User createUser(String name, String lastName, String email, String password) {
        // TODO 4: Usuario registrado: El sistema deberá permitir a los usuarios registrarse ingresando
        //  su nombre, apellido, email y password. Solo se podrá registrar un usuario por email. El usuario deberá
        //  estar registrado para poder ingresar sus pronósticos.

        if (getUserByEmail(email) == null) {
            UserEntity userEntity = new UserEntity();
            userEntity.setName(name);
            userEntity.setEmail(email);
            userEntity.setPassword(password);
            userEntity.setLastName(lastName);
            userRepository.save(userEntity);
            return modelMapper.map(userEntity, User.class);
        }
        throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Email already exists");

        // 1. Search the user with the given email using the method getUserByEmail(email)
        // 2. If the user doesn´t exist, create a new UserEntity and save it using the repository
        // 3. If the user exist, throw an HttpClientErrorException with HttpStatus.BAD_REQUEST
        //    and the message "Email already exists"
    }

    /**
     * Returns all the users in the system
     *
     * @return A list with all the users
     */
    @Override
    public List<User> getAllUsers() {
        List<UserEntity> userEntities = userRepository.findAll();
        return modelMapper.map(userEntities, new TypeToken<List<User>>() {
        }.getType());
    }

    /**
     * Search the user with the given id
     *
     * @param id id of the user
     * @return The user with the given id if it exists or throws an EntityNotFoundException if it doesn´t
     */
    @Override
    public User getUserById(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return modelMapper.map(userEntity, User.class);
    }

    /**
     * Search the user with the given email.
     *
     * @param email email of the user
     * @return The user with the given email or null if it doesn´t exist
     */
    @Override
    public User getUserByEmail(String email) {
        // TODO 4 - Usuario registrado: Crear un método que permita verificar si un email ya existe.

        // 1. Search the user with the given email using the method findByEmail from the repository
        // 2. If the user exists, return it
        // 3. If the user doesn´t exist, return null
        return null;
    }

    /**
     * Search the user with the given email and password to validate the login
     *
     * @param email    email of the user
     * @param password password of the user
     * @return The user with the given email and password or throws an EntityNotFoundException if it doesn´t exist
     */
    @Override
    public User getUserByEmailAndPassword(String email, String password) {
        UserEntity userEntity = userRepository.findByEmailAndPassword(email, password)
                .orElseThrow(EntityNotFoundException::new);
        return modelMapper.map(userEntity, User.class);
    }
}
