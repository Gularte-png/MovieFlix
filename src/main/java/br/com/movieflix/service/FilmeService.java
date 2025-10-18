package br.com.movieflix.service;

import br.com.movieflix.entity.Categoria;
import br.com.movieflix.entity.Filme;
import br.com.movieflix.entity.Streaming;
import br.com.movieflix.entity.response.FilmeResponseDto;
import br.com.movieflix.mapper.FilmeMapper;
import br.com.movieflix.repository.FilmeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class FilmeService {

    private final FilmeRepository filmeRepository;
    private final CategoriaService categoriaService;
    private final StreamingService streamingService;

    public Filme criarFilme(Filme filme) {
        List<Categoria> categoriasEncontradas = new ArrayList<>();
        filme.getCategorias().forEach(categoria ->
                categoriaService.buscarCategoriaPorId(categoria.getId()).ifPresent(categoriasEncontradas::add));
        filme.setCategorias(categoriasEncontradas);
        filme.setStreamings(this.buscarStreamings(filme));

        /*fiz de duas formas diferentes, uma com foreach e outra com for tradicional
        e separando o método e deixando outro dentro*/
        return filmeRepository.save(filme);
    }

    private List<Streaming> buscarStreamings(Filme filme) {
        List<Streaming> streamingsEncontrados = new ArrayList<>();
        for (Streaming streaming : filme.getStreamings()){
            streamingService.buscarStreamingPorId(streaming.getId())
                    .ifPresent(/*essa variavel aqui ao lado representa sempre o objeto retornado pelo servico ali*/
                            streamingEncontrado -> streamingsEncontrados.add(streamingEncontrado));
        }
        return streamingsEncontrados;
    }

    public List<Filme> buscarTodosFilmes(){
        return filmeRepository.findAll();
    }

    public Optional<Filme> buscarFilmePorId(Long id){
        return filmeRepository.findById(id);
    }

    public Optional<Filme> atualizarFilme(Filme filmeAtualizado, Long id) {
        Optional<Filme> filmeOptional = filmeRepository.findById(id);
        if (filmeOptional.isPresent()) {
            List<Streaming> streamings = this.buscarStreamings(filmeAtualizado);

            Filme filme = filmeOptional.get();
            List<Categoria> categoriasEncontradas = new ArrayList<>();
            filme.getCategorias().forEach(categoria ->
                    categoriaService.buscarCategoriaPorId(categoria.getId()).ifPresent(categoriasEncontradas::add));

            filme.getCategorias().clear();
            filme.getStreamings().clear();
            filme.setCategorias(categoriasEncontradas);
            filme.setStreamings(streamings);
            filme.setAvaliacao(filmeAtualizado.getAvaliacao());
            filme.setTitulo(filmeAtualizado.getTitulo());
            filme.setDataDeLançamento(filmeAtualizado.getDataDeLançamento());
            filme.setDescricao(filmeAtualizado.getDescricao());

            return Optional.of(filmeRepository.save(filme));
        }
        return Optional.empty();
    }

    public void deletarFilmePorId(Long id){
        filmeRepository.deleteById(id);
    }

    public List<FilmeResponseDto> buscarFilmesPorCategoria(String categoria){
        List<Categoria> categoriasFiltradas = new ArrayList<>();
        categoriaService.buscarTodasCategorias()
                .stream()
                .filter(variavelTemporaria ->  variavelTemporaria.getNome().equalsIgnoreCase(categoria))
                .findFirst()
                .ifPresent(categoriasFiltradas::add);

        return filmeRepository.findByCategorias(categoriasFiltradas)
                .orElse(List.of())
                .stream()
                .map(FilmeMapper::paraFilmeResponse)
                .toList();
    }


}
