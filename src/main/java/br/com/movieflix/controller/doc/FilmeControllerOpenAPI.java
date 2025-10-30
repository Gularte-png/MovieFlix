package br.com.movieflix.controller.doc;

import br.com.movieflix.entity.request.FilmeRequestDto;
import br.com.movieflix.entity.response.FilmeResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Filme", description = "Operações relacionadas a filmes")
public interface FilmeControllerOpenAPI {

    @Operation(summary = "Cria filme", description = "Realiza a criação de um novo filme")
    @ApiResponse(responseCode = "201", description = "Filme criado com sucesso", content = @Content(schema = @Schema(implementation = FilmeResponseDto.class)))
    ResponseEntity<FilmeResponseDto> criarFilme(FilmeRequestDto filmeRequestDto);

    @Operation(summary = "Busca filme por ID", description = "Realiza a busca de um filme por ID")
    @ApiResponse(responseCode = "200", description = "Filme encontrado com sucesso", content = @Content(schema = @Schema(implementation = FilmeResponseDto.class)))
    ResponseEntity<FilmeResponseDto> buscarFilmePorId(Long id);

    @Operation(summary = "Atualiza filme", description = "Realiza a atualização de um filme existente")
    @ApiResponse(responseCode = "200", description = "Filme atualizado com sucesso", content = @Content(schema = @Schema(implementation = FilmeResponseDto.class)))
    ResponseEntity<FilmeResponseDto> atualizarFilme(Long id, FilmeRequestDto filmeRequestDto);

    @Operation(summary = "Busca todos os filmes", description = "Realiza a busca de todos os filmes")
    @ApiResponse(responseCode = "200", description = "Filmes encontrados com sucesso", content = @Content(schema = @Schema(implementation = FilmeResponseDto.class)))
    ResponseEntity<List<FilmeResponseDto>> buscarTodosFilmes();

    @Operation(summary = "Deleta filme por ID", description = "Realiza a exclusão de um filme por ID")
    @ApiResponse(responseCode = "204", description = "Filme deletado com sucesso")
    ResponseEntity<Void> deletarFilmePorId(Long id);

    @Operation(summary = "Busca filmes por categoria", description = "Realiza a busca de filmes por categoria")
    @ApiResponse(responseCode = "200", description = "Filmes encontrados com sucesso", content = @Content(schema = @Schema(implementation = FilmeResponseDto.class)))
    ResponseEntity<List<FilmeResponseDto>> buscarFilmePorCategoria(String categoria);

}
