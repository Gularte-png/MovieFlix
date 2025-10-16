package br.com.movieflix.controller;

import br.com.movieflix.entity.Filme;
import br.com.movieflix.entity.request.FilmeRequestDto;
import br.com.movieflix.entity.response.FilmeResponseDto;
import br.com.movieflix.mapper.FilmeMapper;
import br.com.movieflix.service.FilmeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filme")
@RequiredArgsConstructor
public class FilmeController {

    private final FilmeService filmeService;

    @PostMapping()
    public ResponseEntity<FilmeResponseDto> criarFilme(@RequestBody FilmeRequestDto filmeRequestDto) {
        Filme novoFilme = FilmeMapper.paraEntidade(filmeRequestDto);
        Filme filme = filmeService.criarFilme(novoFilme);
        return ResponseEntity.status(HttpStatus.CREATED).body(FilmeMapper.paraFilmeResponse(filme));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FilmeResponseDto> buscarFilmePorId(@PathVariable Long id) {
        return filmeService.buscarFilmePorId(id)
                .map(filme -> ResponseEntity.status(HttpStatus.OK).body(FilmeMapper.paraFilmeResponse(filme)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping()
    public ResponseEntity<List<FilmeResponseDto>> buscarTodosFilmes() {
        List<FilmeResponseDto> filmes = filmeService.buscarTodosFilmes()
                .stream()
                .map(FilmeMapper::paraFilmeResponse)
                .toList();
        return !filmes.isEmpty() ? ResponseEntity.ok(filmes) : ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarCategoriaPorId(@PathVariable Long id) {
        filmeService.deletarFilmePorId(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
