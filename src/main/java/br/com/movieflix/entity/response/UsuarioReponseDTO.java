package br.com.movieflix.entity.response;

import lombok.Builder;

@Builder
public record UsuarioReponseDTO(Long id, String nome, String email) {
}
