package idat.pe.navio.service.impl;

import idat.pe.navio.dto.PostulanteRequest;
import idat.pe.navio.dto.PostulanteResponse;
import idat.pe.navio.entity.Postulante;
import idat.pe.navio.exception.BadRequestException;
import idat.pe.navio.mapper.PostulanteMapper;
import idat.pe.navio.repository.PostulanteRepository;
import idat.pe.navio.service.PostulanteService;
import org.springframework.stereotype.Service;

@Service
public class PostulanteServiceImpl implements PostulanteService {

    private final PostulanteRepository postulanteRepository;
    private final PostulanteMapper postulanteMapper;

    public PostulanteServiceImpl(PostulanteRepository postulanteRepository, PostulanteMapper postulanteMapper) {
        this.postulanteRepository = postulanteRepository;
        this.postulanteMapper = postulanteMapper;
    }

    @Override
    public PostulanteResponse registrarPostulante(PostulanteRequest request) {
        if (postulanteRepository.findByNumeroDocumento(request.numeroDocumento()).isPresent()) {
            throw new BadRequestException("El postulante con DNI " + request.numeroDocumento() + " ya se encuentra registrado.");
        }
        
        Postulante postulante = postulanteMapper.toEntity(request);
        Postulante savedPostulante = postulanteRepository.save(postulante);
        
        return postulanteMapper.toResponse(savedPostulante);
    }
}
