package idat.pe.navio.mapper;

import idat.pe.navio.dto.PostulanteRequest;
import idat.pe.navio.dto.PostulanteResponse;
import idat.pe.navio.entity.Postulante;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PostulanteMapper {

    public Postulante toEntity(PostulanteRequest request) {
        if (request == null) return null;
        return Postulante.builder()
                .numeroDocumento(request.numeroDocumento())
                .nombres(request.nombres())
                .apellidos(request.apellidos())
                .correo(request.correo())
                .telefono(request.telefono())
                .fechaRegistro(LocalDateTime.now())
                .estado("PENDIENTE") // As requested
                .build();
    }

    public PostulanteResponse toResponse(Postulante entity) {
        if (entity == null) return null;
        return new PostulanteResponse(
                entity.getId(),
                entity.getNumeroDocumento(),
                entity.getNombres(),
                entity.getApellidos(),
                entity.getCorreo(),
                entity.getTelefono(),
                entity.getFechaRegistro(),
                entity.getEstado()
        );
    }
}
