package controller;

import jakarta.validation.Valid;
import model.User;
import model.request.UserRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.UserService;

@RestController
@RequestMapping("/api")
public class RegisterController {

    private final UserService userService;

    @Autowired
    public RegisterController(UserService userService){
        this.userService=userService;
    }

    @PostMapping("/register")
    public User addUser(@Valid @RequestBody UserRequestDTO userRequestDTO){
        User user = new User();
        user.setName(userRequestDTO.getName());
        user.setEmail(userRequestDTO.getEmail());
        user.setRole(userRequestDTO.getRole());
        return userService.addUser(user);
    }
}
