package idat.pe.navio.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "seguimientos_atencion")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeguimientoAtencion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reclamo_queja_id", nullable = false)
    private ReclamoQueja reclamoQueja;

    @Column(nullable = false)
    private LocalDateTime fechaActualizacion;

    @Column(nullable = false, length = 50)
    private String estadoNuevo;

    @Column(columnDefinition = "TEXT")
    private String observacion;
}
