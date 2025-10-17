package br.com.movieflix.entity.response;

import lombok.Builder;

@Builder
public record FilmeResponseDto(Long id, String titulo, String descricao) {
}
