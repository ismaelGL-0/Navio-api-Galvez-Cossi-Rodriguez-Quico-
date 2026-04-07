package idat.pe.navio.controller;

import idat.pe.navio.dto.PostulanteRequest;
import idat.pe.navio.dto.PostulanteResponse;
import idat.pe.navio.service.PostulanteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/postulantes")
public class PostulanteController {

    private final PostulanteService postulanteService;

    public PostulanteController(PostulanteService postulanteService) {
        this.postulanteService = postulanteService;
    }

    @PostMapping
    public ResponseEntity<PostulanteResponse> registrarPostulante(@RequestBody PostulanteRequest request) {
        PostulanteResponse response = postulanteService.registrarPostulante(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
