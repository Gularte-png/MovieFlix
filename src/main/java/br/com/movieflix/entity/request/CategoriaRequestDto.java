package br.com.movieflix.entity.request;

import lombok.Builder;

@Builder
public record CategoriaRequestDto(String nome) {
}
