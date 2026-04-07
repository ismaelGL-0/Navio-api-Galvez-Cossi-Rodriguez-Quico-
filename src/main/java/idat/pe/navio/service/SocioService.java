package idat.pe.navio.service;

import idat.pe.navio.dto.AutorizarSocioRequest;
import idat.pe.navio.dto.SocioResponse;

public interface SocioService {
    SocioResponse autorizarSocio(AutorizarSocioRequest request);
}
