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
import java.util.Optional;

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

    @PutMapping("/{id}")
    public ResponseEntity<FilmeResponseDto> atualizarFilme(@PathVariable Long id, @RequestBody FilmeRequestDto filmeRequestDto) {
        return filmeService.atualizarFilme(FilmeMapper.paraEntidade(filmeRequestDto), id)
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
    public ResponseEntity<Void> deletarFilmePorId(@PathVariable Long id) {
        Optional<Filme> filmeEncontrado = filmeService.buscarFilmePorId(id);

        if (filmeEncontrado.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        filmeService.deletarFilmePorId(filmeEncontrado.get().getId());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<FilmeResponseDto>> buscarFilmePorCategoria(@RequestParam String categoria) {
        List<FilmeResponseDto> filmesPorCategoria = filmeService.buscarFilmesPorCategoria(categoria);
        return filmesPorCategoria.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.status(HttpStatus.OK).body(filmesPorCategoria);
    }


}
