package com.senai.livros.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senai.livros.entities.livros;
import com.senai.livros.services.LivrosService;
@RestController
@RequestMapping("/livros")
public class LivrosController {

    private final LivrosService livrosService;

    public LivrosController(LivrosService livrosService) {
        this.livrosService = livrosService;
    }

    @PostMapping
    public livros createlivros(@RequestBody livros livros) {
        return livrosService.saveLivros(livros);
    }
   /*
    @GetMapping
    public List<Livros> getAllLivros() {
        return livrosService.getAllLivros();
    }*/

    @GetMapping("/{id}")
    public ResponseEntity<livros> getLivros(@PathVariable Long id) {
        livros livros = livrosService.getLivrosById(id);
        if (livros != null) {
            return ResponseEntity.ok(livros);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<livros>> getAllLivros(RequestEntity<Void> requestEntity) {
        String method = requestEntity.getMethod().name();
        String contentType = requestEntity.getHeaders().getContentType().toString();
        List<livros> livros = livrosService.getAllLivros();
        return ResponseEntity.status(HttpStatus.OK).header("Method", method).header("Content-Type", contentType)
                .body(livros);
    }

    @PutMapping("/{id}")
    public livros updateLivros(@PathVariable Long id, @RequestBody livros livros) {
        return livrosService.updateLivros(id, livros);
    }

    @DeleteMapping("/{id}")
    public void deleteJogo(@PathVariable Long id) {
        livrosService.deleteLivros(id);
    }

}
