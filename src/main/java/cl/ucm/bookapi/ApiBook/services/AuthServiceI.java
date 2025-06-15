package cl.ucm.bookapi.ApiBook.services;

import org.springframework.http.ResponseEntity;

public interface AuthServiceI {

    ResponseEntity<?> register();
    ResponseEntity<?> login();
}
