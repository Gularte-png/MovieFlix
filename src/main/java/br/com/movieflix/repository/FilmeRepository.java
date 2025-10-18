package br.com.movieflix.repository;

import br.com.movieflix.entity.Categoria;
import br.com.movieflix.entity.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long> {

    Optional<List<Filme>> findByCategorias(List<Categoria> categorias);
}
