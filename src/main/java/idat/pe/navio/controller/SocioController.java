package idat.pe.navio.controller;

import idat.pe.navio.dto.AutorizarSocioRequest;
import idat.pe.navio.dto.SocioResponse;
import idat.pe.navio.service.SocioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/socios")
public class SocioController {

    private final SocioService socioService;

    public SocioController(SocioService socioService) {
        this.socioService = socioService;
    }

    @PostMapping("/autorizar")
    public ResponseEntity<SocioResponse> autorizarSocio(@RequestBody AutorizarSocioRequest request) {
        SocioResponse response = socioService.autorizarSocio(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
