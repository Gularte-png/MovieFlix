package br.com.movieflix.mapper;

import br.com.movieflix.entity.Filme;
import br.com.movieflix.entity.request.FilmeRequestDto;
import br.com.movieflix.entity.response.FilmeResponseDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class FilmeMapper {

    public static Filme paraEntidade(FilmeRequestDto filmeRequestDto){
        return Filme
                .builder()
                .titulo(filmeRequestDto.titulo())
                .descricao(filmeRequestDto.descricao())
                .build();
    }

    public static FilmeResponseDto parFilmeResponse(Filme filme){
        return FilmeResponseDto
                .builder()
                .id(filme.getId())
                .titulo(filme.getTitulo())
                .descricao(filme.getDescricao())
                .build();
    }

}
