package br.com.movieflix.controller;

import br.com.movieflix.entity.Categoria;
import br.com.movieflix.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categoria")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

    @GetMapping()
    public List<Categoria> buscarTodasCategorias(){
        return categoriaService.buscarTodasCategorias();
    }
}
