package br.com.challenge.dashboard_api.domain.entitys;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "modulo")
@Data
public class Modulo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nome;
}
