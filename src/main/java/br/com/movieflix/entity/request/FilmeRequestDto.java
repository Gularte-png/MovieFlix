package br.com.movieflix.entity.request;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record FilmeRequestDto(String titulo, String descricao, Double avaliacao, LocalDate dataDeLançamento) {
}
