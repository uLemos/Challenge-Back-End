package br.com.challenge.dashboard_api.domain.repositories;

import br.com.challenge.dashboard_api.domain.entitys.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

  /**
   * Busca todos os tickets com base no ano e mês da data de abertura.
   * Usei @Query para garantir a lógica e performance
   * extraindo o ano e o mês da coluna de timestamp.
   *
   * @param ano O ano para filtrar os tickets.
   * @param mes O mês para filtrar os tickets.
   * @return Uma lista de tickets que correspondem ao filtro.
   */
  @Query("SELECT t FROM Ticket t WHERE month(t.dataAbertura) = :mes")
  List<Ticket> findAllByMonthAcrossYears(@Param("mes") int mes);

  @Query("SELECT t FROM Ticket t WHERE month(t.dataAbertura) = :mes")
  Page<Ticket> findPageByMonthAcrossYears(@Param("mes") int mes, Pageable pageable);

  @Query("SELECT t FROM Ticket t WHERE YEAR(t.dataAbertura) = :ano AND MONTH(t.dataAbertura) = :mes")
  List<Ticket> findAllByYearAndMonth(@Param("ano") int ano, @Param("mes") int mes);

  @Query("SELECT t FROM Ticket t WHERE YEAR(t.dataAbertura) = :ano AND MONTH(t.dataAbertura) = :mes")
  Page<Ticket> findPageByYearAndMonth(@Param("ano") int ano, @Param("mes") int mes, Pageable pageable);
}
