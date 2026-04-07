package idat.pe.navio.dto;

import java.time.LocalDateTime;
import java.util.List;

public record ReclamoResponse(
        Long id,
        String codigoTicket,
        String nombreSocio,
        String nombreTipoAtencion,
        String asunto,
        String descripcion,
        LocalDateTime fechaRegistro,
        String estado,
        List<SeguimientoResponse> seguimientos
) {
}
