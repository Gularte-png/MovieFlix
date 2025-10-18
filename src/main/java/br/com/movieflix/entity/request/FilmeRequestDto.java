package br.com.movieflix.entity.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record FilmeRequestDto(String titulo,
                              String descricao,
                              @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
                              LocalDate dataDeLançamento,
                              double avaliacao,
                              List<Long> categorias,
                              List<Long> streaming) {
}
