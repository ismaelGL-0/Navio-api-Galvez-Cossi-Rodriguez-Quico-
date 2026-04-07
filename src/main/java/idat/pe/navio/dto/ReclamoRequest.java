package idat.pe.navio.dto;

public record ReclamoRequest(
        Long socioId,
        Long tipoAtencionId,
        String asunto,
        String descripcion
) {
}
