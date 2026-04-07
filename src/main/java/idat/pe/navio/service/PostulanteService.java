package idat.pe.navio.service;

import idat.pe.navio.dto.PostulanteRequest;
import idat.pe.navio.dto.PostulanteResponse;

public interface PostulanteService {
    PostulanteResponse registrarPostulante(PostulanteRequest request);
}
