package br.com.challenge.dashboard_api.web.dtos;

import lombok.Getter;
import org.springframework.data.domain.Page;
import java.util.List;

@Getter
public class PagedResultDTO<T> {
  private final List<T> content;
  private final int currentPage;
  private final int totalPages;
  private final long totalElements;

  public PagedResultDTO(Page<T> page) {
    this.content = page.getContent();
    this.currentPage = page.getNumber();
    this.totalPages = page.getTotalPages();
    this.totalElements = page.getTotalElements();
  }
}