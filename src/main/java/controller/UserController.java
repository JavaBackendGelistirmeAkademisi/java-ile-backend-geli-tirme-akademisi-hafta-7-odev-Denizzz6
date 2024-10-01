package controller;

import model.User;
import model.request.UserRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public User addUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        User user = new User();
        user.setName(userRequestDTO.getName());
        user.setRole(userRequestDTO.getRole());
        user.setEmail(userRequestDTO.getEmail());
        user.setComment(userRequestDTO.getComment());
        user.setRate(userRequestDTO.getRate());
        return userService.addUser(user);
    }

    @PostMapping("/addComment")
    @ResponseStatus(HttpStatus.CREATED)
    public User addComment(@Valid @RequestBody User user){
        return userService.addComment(user);
    }

    @PostMapping("/addRate")
    @ResponseStatus(HttpStatus.CREATED)
    public User addRate(@Valid @RequestBody User user){
        return userService.addRate(user);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllUser() {
        return userService.getAllUser();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }
}
