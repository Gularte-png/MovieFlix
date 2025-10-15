package br.com.movieflix.controller;

import br.com.movieflix.entity.Categoria;
import br.com.movieflix.entity.request.CategoriaRequestDto;
import br.com.movieflix.entity.response.CategoriaResponseDto;
import br.com.movieflix.mapper.CategoriaMapper;
import br.com.movieflix.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

    @PostMapping()
    public ResponseEntity<CategoriaResponseDto> criarCategoria(@RequestBody CategoriaRequestDto categoriaRequestDto) {
        Categoria newCategoria = CategoriaMapper.paraEntidade(categoriaRequestDto);
        Categoria categoria = categoriaService.salvarCategoria(newCategoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(CategoriaMapper.paraCategoriaResponse(categoria));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponseDto> buscarCategoriaPorId(@PathVariable Long id) {
        return categoriaService.buscarCategoriaPorId(id)
                .map(categoria -> ResponseEntity.status(HttpStatus.OK).body(CategoriaMapper.paraCategoriaResponse(categoria)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping()
    public ResponseEntity<List<CategoriaResponseDto>> buscarTodasCategorias() {
        List<CategoriaResponseDto> categorias = categoriaService.buscarTodasCategorias().stream().map(CategoriaMapper::paraCategoriaResponse).toList();
        return !categorias.isEmpty() ? ResponseEntity.ok(categorias) : ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarCategoriaPorId(@PathVariable Long id) {
        categoriaService.deletarCategoriaPorId(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
