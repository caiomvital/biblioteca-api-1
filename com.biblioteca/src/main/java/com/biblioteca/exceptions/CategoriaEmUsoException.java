package com.biblioteca.exceptions;

public class CategoriaEmUsoException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public CategoriaEmUsoException(String nome) {
		super("A categoria %s não pode ser removida pois contém livros.".formatted(nome));
	}

}
