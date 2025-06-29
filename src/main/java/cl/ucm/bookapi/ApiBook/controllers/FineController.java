package cl.ucm.bookapi.ApiBook.controllers;

import cl.ucm.bookapi.ApiBook.dto.Fine.FineResponse;
import cl.ucm.bookapi.ApiBook.services.FineService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fine")
public class FineController {
    private final FineService fineService;

    public FineController(
            FineService fineService
    ) {
        this.fineService = fineService;
    }

    @GetMapping("/find/{email}")
    public ResponseEntity<Page<FineResponse>> getFinesByEmail(
            @PathVariable String email,
            Pageable pageable
    ) {
        return ResponseEntity.ok(fineService.getFinesByEmail(pageable, email));
    }

    @PostMapping("/state/{idFine}")
    public ResponseEntity<FineResponse> stateChange (
            @PathVariable Long idFine
    ) {
        return ResponseEntity.ok(fineService.fineStateChange(idFine));
    }
}
