package idat.pe.navio.dto;

public record PostulanteRequest(
        String numeroDocumento,
        String nombres,
        String apellidos,
        String correo,
        String telefono
) {}
