package idat.pe.navio.mapper;

import idat.pe.navio.dto.SocioResponse;
import idat.pe.navio.entity.Socio;
import org.springframework.stereotype.Component;

@Component
public class SocioMapper {

    private final PostulanteMapper postulanteMapper;

    public SocioMapper(PostulanteMapper postulanteMapper) {
        this.postulanteMapper = postulanteMapper;
    }

    public SocioResponse toResponse(Socio entity) {
        if (entity == null) return null;
        return new SocioResponse(
                entity.getId(),
                postulanteMapper.toResponse(entity.getPostulante()),
                entity.getCodigoSocio(),
                entity.getFechaAlta(),
                entity.getEstadoCuenta(),
                entity.getPermisosSistema()
        );
    }
}
