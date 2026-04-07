package idat.pe.navio.repository;

import idat.pe.navio.entity.Socio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SocioRepository extends JpaRepository<Socio, Long> {
    Optional<Socio> findByCodigoSocio(String codigoSocio);
    Optional<Socio> findByPostulanteId(Long postulanteId);
}
