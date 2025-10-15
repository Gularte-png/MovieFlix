package br.com.movieflix.mapper;

import br.com.movieflix.entity.Categoria;
import br.com.movieflix.entity.request.CategoriaRequestDto;
import br.com.movieflix.entity.response.CategoriaResponseDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CategoriaMapper {

    public static Categoria paraEntidade(CategoriaRequestDto categoriaResponseDto){
        return Categoria
                .builder()
                .nome(categoriaResponseDto.nome())
                .build();
    }

    public static CategoriaResponseDto paraCategoriaResponse(Categoria categoria){
        return CategoriaResponseDto
                .builder()
                .id(categoria.getId())
                .nome(categoria.getNome())
                .build();
    }

}
