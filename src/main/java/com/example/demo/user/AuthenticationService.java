package com.example.demo.user;

import com.example.demo.request.LoginRequest;

import java.util.List;
import java.util.Optional;

public interface AuthenticationService {

    void saveUser(UserDto userDto);

    Optional<User>  findUserByEmail(String email);

    List<UserDto> findAllUsers();

    String login(LoginRequest authenticationRequest);
}
