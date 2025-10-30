package br.com.movieflix.controller.doc;

import br.com.movieflix.entity.request.CategoriaRequestDto;
import br.com.movieflix.entity.response.CategoriaResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Categoria", description = "Operações relacionadas a categoria")
public interface CategoriaControllerOpenAPI {

    @Operation(summary = "Cria categoria", description = "Realiza a criação de categoria")
    @ApiResponse(responseCode = "201", description = "Categoria criada com sucesso", content = @Content(schema = @Schema(implementation = CategoriaResponseDto.class)))
    ResponseEntity<CategoriaResponseDto> criarCategoria(CategoriaRequestDto categoriaRequestDto);

    @Operation(summary = "Busca categoria por ID", description = "Realiza a busca de categoria por ID")
    @ApiResponse(responseCode = "200", description = "Categoria encontrada com sucesso", content = @Content(schema = @Schema(implementation = CategoriaResponseDto.class)))
    ResponseEntity<CategoriaResponseDto> buscarCategoriaPorId(Long id);

    @Operation(summary = "Busca todas as categorias", description = "Realiza a busca de todas as categorias")
    @ApiResponse(responseCode = "200", description = "Categorias encontradas com sucesso", content = @Content(schema = @Schema(implementation = CategoriaResponseDto.class)))
    ResponseEntity<List<CategoriaResponseDto>> buscarTodasCategorias();

    @Operation(summary = "Deleta categoria por ID", description = "Realiza a exclusão de uma categoria por ID")
    @ApiResponse(responseCode = "204", description = "Categoria deletada com sucesso")
    ResponseEntity<Void> deletarCategoriaPorId(Long id);

}