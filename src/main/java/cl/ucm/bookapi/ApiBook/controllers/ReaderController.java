package cl.ucm.bookapi.ApiBook.controllers;

import cl.ucm.bookapi.ApiBook.dto.Reader.ReaderResponse;
import cl.ucm.bookapi.ApiBook.services.ReaderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/reader")
public class ReaderController {
    private final ReaderService readerService;

    public ReaderController(
            ReaderService readerService
    ) {
        this.readerService = readerService;
    }

    @GetMapping("/find/{email}")
    public ResponseEntity<Page<ReaderResponse>> getReaderByEmail(
            @PathVariable String email,
            Pageable pageable
    ) {
        return ResponseEntity.ok(readerService.getReadersByEmail(pageable, email));
    }

    @PostMapping("/state/{email}")
    public ResponseEntity<ReaderResponse> stateChangeReader(
        @PathVariable String email
    ) {
        return ResponseEntity.ok(readerService.stateChangeReader(email));
    }
}
