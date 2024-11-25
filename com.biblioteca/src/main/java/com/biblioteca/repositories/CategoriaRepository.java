package com.biblioteca.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biblioteca.models.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

	Optional<Categoria> findByNomeContainingIgnoreCase(String nome);
	List<Categoria> findAllByNomeContainingIgnoreCase(String nome);
	boolean existsByNomeIgnoreCase(String nome);
    boolean existsByIdAndLivrosIsNotEmpty(Long id);


	

}
