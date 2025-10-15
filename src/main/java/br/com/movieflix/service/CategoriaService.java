package br.com.movieflix.service;

import br.com.movieflix.entity.Categoria;
import br.com.movieflix.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository repository;

    public List<Categoria> buscarTodasCategorias(){
        return repository.findAll();
    }

    public Categoria salvarCategoria(Categoria categoria){
        return repository.save(categoria);
    }

    public Optional<Categoria> buscarCategoriaPorId(Long id) {
        return repository.findById(id);
    }

    public void deletarCategoriaPorId(Long id) {
        repository.deleteById(id);
    }
}
