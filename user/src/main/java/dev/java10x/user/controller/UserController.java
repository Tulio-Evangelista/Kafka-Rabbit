package dev.java10x.user.controller;


import dev.java10x.user.domain.UserModel;
import dev.java10x.user.dto.UserDto;
import dev.java10x.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public ResponseEntity<UserModel> createUser(@RequestBody UserDto userDto) {
        var userModel = new UserModel();
        BeanUtils.copyProperties(userDto, userModel);
        userModel = userService.save(userModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(userModel);
    }


}
