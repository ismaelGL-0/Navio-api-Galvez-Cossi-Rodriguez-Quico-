package idat.pe.navio.dto;

import java.time.LocalDateTime;

public record SeguimientoResponse(
        LocalDateTime fechaActualizacion,
        String estadoNuevo,
        String observacion
) {
}
