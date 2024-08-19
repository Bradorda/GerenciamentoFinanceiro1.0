package Projeto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import Projeto.model.MovimentacaoDAO;
import Projeto.movimentacao.Movimentacao;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/movimentacoes")
public class MovimentacaoController {

    @Autowired
    private MovimentacaoDAO movimentacaoDAO;

    // Criar uma nova movimentacao
    @PostMapping
    public ResponseEntity<Movimentacao> createMovimentacao(@RequestBody Movimentacao movimentacao) {
        if (movimentacao.getTipo() == null || movimentacao.getTipo().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        boolean isCreated = movimentacaoDAO.create(movimentacao);
        if (isCreated) {
            return new ResponseEntity<>(movimentacao, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Atualizar uma movimentacao existente
    @PutMapping("/{id}")
    public ResponseEntity<Movimentacao> updateMovimentacao(@PathVariable("id") int id, @RequestBody Movimentacao movimentacao) {
        if (movimentacao.getTipo() == null || movimentacao.getTipo().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        movimentacao.setPk_movimentacao(id);
        boolean isUpdated = movimentacaoDAO.update(movimentacao);
        if (isUpdated) {
            return new ResponseEntity<>(movimentacao, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Excluir uma movimentacao pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovimentacao(@PathVariable("id") int id) {
        boolean isDeleted = movimentacaoDAO.delete(id);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Recuperar uma movimentacao pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Movimentacao> getMovimentacao(@PathVariable("id") int id) {
        Movimentacao movimentacao = movimentacaoDAO.retrieve(id);
        if (movimentacao != null) {
            return new ResponseEntity<>(movimentacao, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Recuperar todas as movimentacoes
    @GetMapping
    public ResponseEntity<List<Movimentacao>> getAllMovimentacoes() {
        List<Movimentacao> movimentacoes = movimentacaoDAO.retrieveAll();
        if (movimentacoes != null && !movimentacoes.isEmpty()) {
            return new ResponseEntity<>(movimentacoes, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    // Recuperar movimentacoes por data
    @GetMapping("/data")
    public ResponseEntity<List<Movimentacao>> getMovimentacoesByDate(
        @RequestParam("data") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate data) {
        List<Movimentacao> movimentacoes = movimentacaoDAO.retrieveByDate(data);
        if (movimentacoes != null && !movimentacoes.isEmpty()) {
            return new ResponseEntity<>(movimentacoes, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
