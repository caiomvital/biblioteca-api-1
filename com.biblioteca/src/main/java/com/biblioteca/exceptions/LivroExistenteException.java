package com.biblioteca.exceptions;

public class LivroExistenteException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public LivroExistenteException(String titulo, String autor) {
		super("Um livro com título %s e autor %s já existe.".formatted(titulo, autor));
	}

}
