package cl.ucm.bookapi.ApiBook.services;

import cl.ucm.bookapi.ApiBook.dto.Auth.LoginRequest;
import cl.ucm.bookapi.ApiBook.dto.Auth.LoginResponse;
import cl.ucm.bookapi.ApiBook.dto.Auth.RegisterRequest;
import cl.ucm.bookapi.ApiBook.models.User;
import org.springframework.http.ResponseEntity;

public interface AuthServiceI {

    User register(RegisterRequest registerRequest);
    LoginResponse login(LoginRequest loginRequest);
}
