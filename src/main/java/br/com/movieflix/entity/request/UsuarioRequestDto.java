package br.com.movieflix.entity.request;

import lombok.Builder;

@Builder
public record UsuarioRequestDto(String nome, String email, String senha) {
}
