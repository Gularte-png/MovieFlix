package br.com.movieflix.service;

import br.com.movieflix.entity.Filme;
import br.com.movieflix.repository.FilmeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FilmeService {

    private final FilmeRepository repository;

    public List<Filme> buscarTodosFilmes(){
        return repository.findAll();
    }

    public Filme salvarFilme(Filme filme){
        return repository.save(filme);
    }

    public Optional<Filme> buscarFilmePorId(Long id) {
        return repository.findById(id);
    }

    public void deletarFilmePorId(Long id) {
        repository.deleteById(id);
    }
}
