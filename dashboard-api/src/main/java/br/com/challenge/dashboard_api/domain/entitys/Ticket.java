package br.com.challenge.dashboard_api.domain.entitys;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "ticket")
@Data
public class Ticket {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String titulo;

  @Column(name = "dataabertura")
  private LocalDateTime dataAbertura;

  @Column(name = "dataencerramento")
  private LocalDateTime dataEncerramento;

  @ManyToOne
  @JoinColumn(name = "codcliente")
  private Cliente cliente;

  @ManyToOne
  @JoinColumn(name = "codmodulo")
  private Modulo modulo;
}
