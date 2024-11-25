package com.biblioteca.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	
	// lidando com exceções não tratadas por exceções específicas
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleGenericException(Exception ex) {
		return ResponseEntity.status(500).body("Erro inesperado: " + ex.getMessage());
	}
	
	//lidando com a exceção genérica ResourceNotFoundException
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<String> handleResourceNotFound(ResourceNotFoundException ex){
		return ResponseEntity.status(404).body(ex.getMessage());
	}
	
	//lidando com as exceções de Categoria
	
	@ExceptionHandler(CategoriaEmUsoException.class)
	public ResponseEntity<String> handleCategoriaEmUsoException(CategoriaEmUsoException ex){
		return ResponseEntity.status(409).body(ex.getMessage());
	}
	
	@ExceptionHandler(CategoriaExistenteException.class)
	public ResponseEntity<String> handleCategoriaExistenteException(CategoriaExistenteException ex) {
		return ResponseEntity.status(409).body(ex.getMessage());
	}
	
	@ExceptionHandler(CategoriaNaoEncontradaException.class)
	public ResponseEntity<String> handleCategoriaNaoEncontradaException(CategoriaNaoEncontradaException ex){
		return ResponseEntity.status(404).body(ex.getMessage());
	}
	
	//lidando com as exceções de Categoria

	@ExceptionHandler(LivroExistenteException.class)
	public ResponseEntity<String> handleLivroExistenteException(LivroExistenteException ex) {
		return ResponseEntity.status(409).body(ex.getMessage());
	}
	
	@ExceptionHandler(LivroNaoEncontradoException.class)
	public ResponseEntity<String> handleLivroNaoEncontradoException(LivroNaoEncontradoException ex) {
		return ResponseEntity.status(404).body(ex.getMessage());
	}
	
	
	
}
