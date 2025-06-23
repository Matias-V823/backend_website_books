package cl.ucm.bookapi.ApiBook.controllers;

import cl.ucm.bookapi.ApiBook.dto.Auth.LoginRequest;
import cl.ucm.bookapi.ApiBook.dto.Auth.LoginResponse;
import cl.ucm.bookapi.ApiBook.dto.Auth.RegisterRequest;
import cl.ucm.bookapi.ApiBook.dto.Auth.RegisterResponse;
import cl.ucm.bookapi.ApiBook.models.Rol;
import cl.ucm.bookapi.ApiBook.models.User;
import cl.ucm.bookapi.ApiBook.repository.RolRepository;
import cl.ucm.bookapi.ApiBook.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    ResponseEntity<RegisterResponse> registerNewUser(
            @RequestBody @Valid RegisterRequest registerRequest
    ) {
        User user = authService.register(registerRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new RegisterResponse(user));
    }

    @PostMapping("/login")
    ResponseEntity<LoginResponse> login(
            @RequestBody @Valid LoginRequest loginRequest
    ) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }

}
