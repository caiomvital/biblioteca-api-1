package com.biblioteca.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biblioteca.models.Livro;

public interface LivroRepository extends JpaRepository <Livro, Long> {

	List<Livro> findAllByCategoriaNome(String nome);
    boolean existsByTituloAndAutorIgnoreCase(String titulo, String autor);


}
