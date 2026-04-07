package idat.pe.navio.mapper;

import idat.pe.navio.dto.ReclamoResponse;
import idat.pe.navio.dto.SeguimientoResponse;
import idat.pe.navio.entity.ReclamoQueja;
import idat.pe.navio.entity.SeguimientoAtencion;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReclamoMapper {

    public ReclamoResponse toReclamoResponse(ReclamoQueja reclamo, List<SeguimientoAtencion> seguimientos) {
        if (reclamo == null) {
            return null;
        }

        String nombreSocio = null;
        if (reclamo.getSocio() != null && reclamo.getSocio().getPostulante() != null) {
            nombreSocio = reclamo.getSocio().getPostulante().getNombres() + " " + reclamo.getSocio().getPostulante().getApellidos();
        }

        String nombreTipoAtencion = null;
        if (reclamo.getTipoAtencion() != null) {
            nombreTipoAtencion = reclamo.getTipoAtencion().getNombre();
        }

        List<SeguimientoResponse> seguimientosResponse = seguimientos != null ?
                seguimientos.stream().map(this::toSeguimientoResponse).collect(Collectors.toList()) :
                List.of();

        return new ReclamoResponse(
                reclamo.getId(),
                reclamo.getCodigoTicket(),
                nombreSocio,
                nombreTipoAtencion,
                reclamo.getAsunto(),
                reclamo.getDescripcion(),
                reclamo.getFechaRegistro(),
                reclamo.getEstado(),
                seguimientosResponse
        );
    }

    public SeguimientoResponse toSeguimientoResponse(SeguimientoAtencion seguimiento) {
        if (seguimiento == null) {
            return null;
        }

        return new SeguimientoResponse(
                seguimiento.getFechaActualizacion(),
                seguimiento.getEstadoNuevo(),
                seguimiento.getObservacion()
        );
    }
}
