package com.example.demo.user;

import com.example.demo.security.JwtUtil;
import com.example.demo.user.User;
import com.example.demo.request.LoginRequest;
import com.example.demo.response.ErrorResponse;
import com.example.demo.response.LoginResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin(origins = "http://localhost:4200")
@Controller
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;
    public AuthenticationController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody LoginRequest loginRequest)  {
        try {
            Authentication authentication =
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            String email = authentication.getName();
            User user = new User(email,"");
            String token = jwtUtil.createToken(user);
            LoginResponse loginResponse = new LoginResponse(email,token);

            return ResponseEntity.ok(loginRequest);

        }catch (BadCredentialsException e){
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST,"Invalid username or password");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }catch (Exception e){
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }
}