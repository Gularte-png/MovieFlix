package br.com.movieflix.entity.response;

import lombok.Builder;

@Builder
public record StreamingResponseDto(Long id, String nome) {
}
