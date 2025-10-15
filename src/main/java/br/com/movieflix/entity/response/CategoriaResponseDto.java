package br.com.movieflix.entity.response;

import lombok.Builder;

@Builder
public record CategoriaResponseDto(Long id, String nome) {
}
