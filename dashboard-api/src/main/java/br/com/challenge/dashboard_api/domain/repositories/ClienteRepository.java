package br.com.challenge.dashboard_api.domain.repositories;

import br.com.challenge.dashboard_api.domain.entitys.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
