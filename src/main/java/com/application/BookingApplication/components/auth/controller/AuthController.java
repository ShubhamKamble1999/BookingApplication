package com.application.BookingApplication.components.auth.controller;

import com.application.BookingApplication.components.JWT.JwtRequest;
import com.application.BookingApplication.components.JWT.JwtResponse;
import com.application.BookingApplication.components.auth.services.AuthService;
import com.application.BookingApplication.components.user.model.UserModel;
import com.application.BookingApplication.security.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request){

        this.doAuthenticate(request.getUserId(),request.getPassword());

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUserId());
        String token = this.jwtHelper.generateToken(userDetails);

        JwtResponse response = JwtResponse.builder()
                .jwtToken(token)
                .userId(userDetails.getUsername()).build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/signup")
    private ResponseEntity<Object> signUp(@RequestBody UserModel userModelData){
        UserModel userModel = authService.signUp(userModelData);
        System.out.println("--------------------------------------------");
        System.out.println(" USERMODEL :: "+userModel);
        System.out.println("--------------------------------------------");
        return ResponseEntity.ok(userModel);
    }

    private void doAuthenticate(String userId, String password){
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userId,password);
        try {
            manager.authenticate(authentication);
        }catch (BadCredentialsException badCredentialsException){
            throw new RuntimeException("Invalid Username and Password");
        }
    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler(){
        return "Credential Invalid";
    }
}
