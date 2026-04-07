package idat.pe.navio.controller;

import idat.pe.navio.dto.ReclamoRequest;
import idat.pe.navio.dto.ReclamoResponse;
import idat.pe.navio.service.ReclamoQuejaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reclamos")
@RequiredArgsConstructor
public class ReclamoQuejaController {

    private final ReclamoQuejaService reclamoQuejaService;

    @PostMapping
    public ResponseEntity<ReclamoResponse> registrarReclamo(@RequestBody ReclamoRequest request) {
        ReclamoResponse response = reclamoQuejaService.registrarReclamo(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/seguimiento/{codigoTicket}")
    public ResponseEntity<ReclamoResponse> consultarSeguimiento(@PathVariable String codigoTicket) {
        ReclamoResponse response = reclamoQuejaService.consultarSeguimiento(codigoTicket);
        return ResponseEntity.ok(response);
    }
}
