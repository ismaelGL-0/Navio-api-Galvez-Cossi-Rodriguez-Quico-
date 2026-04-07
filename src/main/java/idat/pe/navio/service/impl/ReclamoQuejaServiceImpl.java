package idat.pe.navio.service.impl;

import idat.pe.navio.dto.ReclamoRequest;
import idat.pe.navio.dto.ReclamoResponse;
import idat.pe.navio.entity.ReclamoQueja;
import idat.pe.navio.entity.SeguimientoAtencion;
import idat.pe.navio.entity.Socio;
import idat.pe.navio.entity.TipoAtencion;
import idat.pe.navio.exception.ResourceNotFoundException;
import idat.pe.navio.mapper.ReclamoMapper;
import idat.pe.navio.repository.ReclamoQuejaRepository;
import idat.pe.navio.repository.SeguimientoAtencionRepository;
import idat.pe.navio.repository.SocioRepository;
import idat.pe.navio.repository.TipoAtencionRepository;
import idat.pe.navio.service.ReclamoQuejaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReclamoQuejaServiceImpl implements ReclamoQuejaService {

    private final SocioRepository socioRepository;
    private final TipoAtencionRepository tipoAtencionRepository;
    private final ReclamoQuejaRepository reclamoQuejaRepository;
    private final SeguimientoAtencionRepository seguimientoAtencionRepository;
    private final ReclamoMapper reclamoMapper;

    @Override
    @Transactional
    public ReclamoResponse registrarReclamo(ReclamoRequest request) {
        Socio socio = socioRepository.findById(request.socioId())
                .orElseThrow(() -> new ResourceNotFoundException("Socio no encontrado con el id: " + request.socioId()));

        TipoAtencion tipoAtencion = tipoAtencionRepository.findById(request.tipoAtencionId())
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de atención no encontrado con el id: " + request.tipoAtencionId()));

        String codigoTicket = "TCK-" + LocalDateTime.now().getYear() + "-" + UUID.randomUUID().toString().substring(0, 4).toUpperCase();

        ReclamoQueja reclamo = ReclamoQueja.builder()
                .codigoTicket(codigoTicket)
                .socio(socio)
                .tipoAtencion(tipoAtencion)
                .asunto(request.asunto())
                .descripcion(request.descripcion())
                .fechaRegistro(LocalDateTime.now())
                .estado("REGISTRADO")
                .build();

        ReclamoQueja reclamoGuardado = reclamoQuejaRepository.save(reclamo);

        SeguimientoAtencion seguimiento = SeguimientoAtencion.builder()
                .reclamoQueja(reclamoGuardado)
                .fechaActualizacion(LocalDateTime.now())
                .estadoNuevo("REGISTRADO")
                .observacion("Ticket creado y derivado para su revisión")
                .build();

        SeguimientoAtencion seguimientoGuardado = seguimientoAtencionRepository.save(seguimiento);

        return reclamoMapper.toReclamoResponse(reclamoGuardado, List.of(seguimientoGuardado));
    }

    @Override
    public ReclamoResponse consultarSeguimiento(String codigoTicket) {
        ReclamoQueja reclamo = reclamoQuejaRepository.findByCodigoTicket(codigoTicket)
                .orElseThrow(() -> new ResourceNotFoundException("Reclamo no encontrado con el ticket: " + codigoTicket));

        List<SeguimientoAtencion> seguimientos = seguimientoAtencionRepository.findByReclamoQuejaIdOrderByFechaActualizacionAsc(reclamo.getId());

        return reclamoMapper.toReclamoResponse(reclamo, seguimientos);
    }
}
