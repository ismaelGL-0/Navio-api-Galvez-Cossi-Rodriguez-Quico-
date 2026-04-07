package idat.pe.navio.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "socios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Socio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postulante_id", nullable = false, unique = true)
    private Postulante postulante;

    @Column(nullable = false, unique = true, length = 20)
    private String codigoSocio;

    @Column(nullable = false)
    private LocalDateTime fechaAlta;

    @Column(nullable = false, length = 20)
    private String estadoCuenta;

    @Column(length = 255)
    private String permisosSistema;
}
