package idat.pe.navio.service;

import idat.pe.navio.dto.ReclamoRequest;
import idat.pe.navio.dto.ReclamoResponse;

public interface ReclamoQuejaService {
    ReclamoResponse registrarReclamo(ReclamoRequest request);
    ReclamoResponse consultarSeguimiento(String codigoTicket);
}
