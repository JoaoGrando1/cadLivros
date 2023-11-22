package com.senai.livros.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.senai.livros.entities.livros;
import com.senai.livros.repository.livrosRepository;

@Service
public class LivrosService {
    private final livrosRepository livrosRepository;

    public LivrosService(livrosRepository livrosRepository) {
        this.livrosRepository = livrosRepository;
    }

    public livros getLivrosById(Long id) {
        return livrosRepository.findById(id).orElse(null);

    }

    public livros saveLivros(livros livros) {
        return livrosRepository.save(livros);
    }

    public List<livros> getAllLivros() {
        return livrosRepository.findAll();
    }

    public void deleteLivros(Long id) {
        livrosRepository.deleteById(id);
    }

    public livros updateLivros(Long id, livros novoLivros) {
        Optional<livros> livrosOptional = livrosRepository.findById(id);
        if (livrosOptional.isPresent()) {
            livros livrosExistente = livrosOptional.get();
            livrosExistente.setDescricao(novoLivros.getDescricao());
            livrosExistente.setIsbn(novoLivros.getIsbn());
            return livrosRepository.save(livrosExistente);
        } else {
            return null;
        }
    }
}