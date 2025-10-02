package br.com.movieflix.service;

import br.com.movieflix.entity.Categoria;
import br.com.movieflix.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository repository;

    public List<Categoria> buscarTodasCategorias(){
        return repository.findAll();
    }
}
