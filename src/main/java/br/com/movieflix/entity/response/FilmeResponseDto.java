package br.com.movieflix.entity.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record FilmeResponseDto(Long id,
                               String titulo,
                               String descricao,
                               @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/mm/yyyy")
                               LocalDate dataDeLançamento,
                               double avaliacao,
                               List<CategoriaResponseDto> categorias,
                               List<StreamingResponseDto> streaming) {
}
