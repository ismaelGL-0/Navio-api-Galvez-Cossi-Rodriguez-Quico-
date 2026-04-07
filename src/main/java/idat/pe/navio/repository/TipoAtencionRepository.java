package idat.pe.navio.repository;

import idat.pe.navio.entity.TipoAtencion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoAtencionRepository extends JpaRepository<TipoAtencion, Long> {
}
