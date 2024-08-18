package Projeto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import Projeto.model.CategoriaDAO;
import Projeto.movimentacao.Categoria;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaDAO categoriaDAO;

    // Criar uma nova categoria
    @PostMapping
    public ResponseEntity<Categoria> createCategoria(@RequestBody Categoria categoria) {
        boolean isCreated = categoriaDAO.create(categoria);
        if (isCreated) {
            return new ResponseEntity<>(categoria, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Atualizar uma categoria existente
    @PutMapping("/{id}")
    public ResponseEntity<Categoria> updateCategoria(@PathVariable("id") int id, @RequestBody Categoria categoria) {
        categoria.setPk_categoria(id);
        boolean isUpdated = categoriaDAO.update(categoria);
        if (isUpdated) {
            return new ResponseEntity<>(categoria, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Excluir uma categoria pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoria(@PathVariable("id") int id) {
        boolean isDeleted = categoriaDAO.delete(id);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Recuperar uma categoria pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> getCategoria(@PathVariable("id") int id) {
        Categoria categoria = categoriaDAO.retrieve(id);
        if (categoria != null) {
            return new ResponseEntity<>(categoria, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Recuperar todas as categorias
    @GetMapping
    public ResponseEntity<List<Categoria>> getAllCategorias() {
        List<Categoria> categorias = categoriaDAO.retrieveAll();
        if (categorias != null && !categorias.isEmpty()) {
            return new ResponseEntity<>(categorias, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
