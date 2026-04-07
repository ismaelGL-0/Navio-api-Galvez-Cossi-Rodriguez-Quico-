package idat.pe.navio.dto;

import java.time.LocalDateTime;

public record PostulanteResponse(
        Long id,
        String numeroDocumento,
        String nombres,
        String apellidos,
        String correo,
        String telefono,
        LocalDateTime fechaRegistro,
        String estado
) {}
