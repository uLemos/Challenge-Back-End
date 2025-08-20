package br.com.challenge.dashboard_api.domain.repositories;

import br.com.challenge.dashboard_api.domain.entitys.Modulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuloRepository extends JpaRepository<Modulo, Long> {
}
