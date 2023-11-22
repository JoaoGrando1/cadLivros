package com.senai.livros.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.senai.livros.entities.livros;

public interface livrosRepository extends JpaRepository<livros, Long>{



}