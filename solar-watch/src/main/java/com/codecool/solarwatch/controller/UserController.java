package com.codecool.solarwatch.controller;

import com.codecool.solarwatch.model.payload.UserRequest;
import com.codecool.solarwatch.service.UserEntityService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {

    private final UserEntityService userService;

    public UserController(UserEntityService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> createUser(@RequestBody UserRequest signUpRequest) {
      userService.createUser(signUpRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody UserRequest loginRequest) {
        return ResponseEntity.ok(userService.authenticateUser(loginRequest));
    }


}
