package idat.pe.navio.repository;

import idat.pe.navio.entity.SeguimientoAtencion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeguimientoAtencionRepository extends JpaRepository<SeguimientoAtencion, Long> {
    List<SeguimientoAtencion> findByReclamoQuejaIdOrderByFechaActualizacionAsc(Long reclamoQuejaId);
}
