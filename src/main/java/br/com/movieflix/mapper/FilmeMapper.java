package br.com.movieflix.mapper;

import br.com.movieflix.entity.Categoria;
import br.com.movieflix.entity.Filme;
import br.com.movieflix.entity.Streaming;
import br.com.movieflix.entity.request.FilmeRequestDto;
import br.com.movieflix.entity.response.CategoriaResponseDto;
import br.com.movieflix.entity.response.FilmeResponseDto;
import br.com.movieflix.entity.response.StreamingResponseDto;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class FilmeMapper {

    public static Filme paraEntidade(FilmeRequestDto filmeRequestDto){

        List<Streaming> streamings = filmeRequestDto.streaming().stream()
                .map(streamingId -> Streaming.builder().id(streamingId).build())
                .toList();

        List<Categoria> categorias = filmeRequestDto.categorias().stream()
                .map(categoriId -> Categoria.builder().id(categoriId).build())
                .toList();

        return Filme
                .builder()
                .titulo(filmeRequestDto.titulo())
                .descricao(filmeRequestDto.descricao())
                .dataDeLançamento(filmeRequestDto.dataDeLançamento())
                .avaliacao(filmeRequestDto.avaliacao())
                .categorias(categorias)
                .streamings(streamings)
                .build();
    }

    public static FilmeResponseDto paraFilmeResponse(Filme filme){
        List<CategoriaResponseDto> categorias = filme.getCategorias().stream()
                .map(CategoriaMapper::paraCategoriaResponse)
                .toList();

        List<StreamingResponseDto> streamings = filme.getStreamings().stream()
                .map(StreamingMapper::paraStreamingResponseDto)
                .toList();

        return FilmeResponseDto
                .builder()
                .id(filme.getId())
                .titulo(filme.getTitulo())
                .dataDeLançamento(filme.getDataDeLançamento())
                .descricao(filme.getDescricao())
                .avaliacao(filme.getAvaliacao())
                .categorias(categorias)
                .streaming(streamings)
                .build();
    }

}
