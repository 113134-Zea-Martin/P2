package ar.edu.utn.frc.tup.lc.iii.controllers;

import ar.edu.utn.frc.tup.lc.iii.dtos.SignInDto;
import ar.edu.utn.frc.tup.lc.iii.dtos.SignUpDto;
import ar.edu.utn.frc.tup.lc.iii.dtos.UserDto;
import ar.edu.utn.frc.tup.lc.iii.models.User;
import ar.edu.utn.frc.tup.lc.iii.services.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Method to get all users.
     *
     * @return all users.
     */
    @GetMapping("")
    public ResponseEntity<List<UserDto>> getUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(modelMapper.map(users, new TypeToken<List<UserDto>>() {}.getType()));
    }

    /**
     * Method to get a user by id.
     *
     * @param id user id
     * @return the user.
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUsers(@PathVariable("id") Long id){
        User user = userService.getUserById(id);
        return ResponseEntity.ok(modelMapper.map(user, UserDto.class));
    }

    /**
     * Method to check if an email is available.
     *
     * @param email email to check
     * @return true if the email is available, false otherwise.
     */
    @GetMapping("/available-email")
    public ResponseEntity<Boolean> checkAvailableEmail(@RequestParam("email") @Valid @Email String email) {
        User user = userService.getUserByEmail(email);
        return ResponseEntity.ok(user == null);
    }

    /**
     * Method to sign in a user.
     *
     * @param signInDto sign in data
     * @return the user.
     */
    @PostMapping("/sign-in")
    public ResponseEntity<UserDto> signIn(@RequestBody @Valid SignInDto signInDto) {
        User user = userService.getUserByEmailAndPassword(signInDto.getEmail(), signInDto.getPassword());
        return ResponseEntity.ok(modelMapper.map(user, UserDto.class));
    }

    /**
     * Method to create a user.
     *
     * @param signUpDto sign up data
     * @return the user.
     */
    @PostMapping("/sign-up")
    public ResponseEntity<UserDto> signUp(@RequestBody @Valid SignUpDto signUpDto) {
        User user = userService.createUser(signUpDto.getName(), signUpDto.getLastName(), signUpDto.getEmail(), signUpDto.getPassword());
        return ResponseEntity.ok(modelMapper.map(user, UserDto.class));
    }
}
