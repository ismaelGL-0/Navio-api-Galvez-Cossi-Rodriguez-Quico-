package idat.pe.navio.repository;

import idat.pe.navio.entity.Postulante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostulanteRepository extends JpaRepository<Postulante, Long> {
    Optional<Postulante> findByNumeroDocumento(String numeroDocumento);
    Optional<Postulante> findByCorreo(String correo);
}
