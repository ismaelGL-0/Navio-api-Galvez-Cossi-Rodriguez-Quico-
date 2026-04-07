package idat.pe.navio.repository;

import idat.pe.navio.entity.ReclamoQueja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReclamoQuejaRepository extends JpaRepository<ReclamoQueja, Long> {
    Optional<ReclamoQueja> findByCodigoTicket(String codigoTicket);
}
