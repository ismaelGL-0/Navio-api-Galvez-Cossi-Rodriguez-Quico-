package idat.pe.navio.service.impl;

import idat.pe.navio.dto.AutorizarSocioRequest;
import idat.pe.navio.dto.SocioResponse;
import idat.pe.navio.entity.Postulante;
import idat.pe.navio.entity.Socio;
import idat.pe.navio.exception.BadRequestException;
import idat.pe.navio.exception.ResourceNotFoundException;
import idat.pe.navio.mapper.SocioMapper;
import idat.pe.navio.repository.PostulanteRepository;
import idat.pe.navio.repository.SocioRepository;
import idat.pe.navio.service.SocioService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class SocioServiceImpl implements SocioService {

    private final SocioRepository socioRepository;
    private final PostulanteRepository postulanteRepository;
    private final SocioMapper socioMapper;

    public SocioServiceImpl(SocioRepository socioRepository, PostulanteRepository postulanteRepository, SocioMapper socioMapper) {
        this.socioRepository = socioRepository;
        this.postulanteRepository = postulanteRepository;
        this.socioMapper = socioMapper;
    }

    @Transactional
    @Override
    public SocioResponse autorizarSocio(AutorizarSocioRequest request) {
        Postulante postulante = postulanteRepository.findById(request.postulanteId())
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el postulante con ID: " + request.postulanteId()));

        if (!"PENDIENTE".equalsIgnoreCase(postulante.getEstado())) {
            throw new BadRequestException("El postulante no se encuentra en estado PENDIENTE.");
        }

        if (socioRepository.findByPostulanteId(postulante.getId()).isPresent()) {
            throw new BadRequestException("El postulante ya ha sido autorizado como socio.");
        }

        // Cambiar estado a APROBADO
        postulante.setEstado("APROBADO");
        postulanteRepository.save(postulante);

        // Generar codigoSocio único (formato SOC-YYYY-XXX)
        String year = String.valueOf(LocalDateTime.now().getYear());
        String formatId = String.format("%03d", postulante.getId());
        String codigoSocio = "SOC-" + year + "-" + formatId;

        Socio socio = Socio.builder()
                .postulante(postulante)
                .codigoSocio(codigoSocio)
                .fechaAlta(LocalDateTime.now())
                .estadoCuenta("AL_DIA")
                .permisosSistema(request.permisosSistema())
                .build();

        Socio savedSocio = socioRepository.save(socio);

        return socioMapper.toResponse(savedSocio);
    }
}
