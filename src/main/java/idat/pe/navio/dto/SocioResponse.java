package idat.pe.navio.dto;

import java.time.LocalDateTime;

public record SocioResponse(
        Long id,
        PostulanteResponse postulante,
        String codigoSocio,
        LocalDateTime fechaAlta,
        String estadoCuenta,
        String permisosSistema
) {}
