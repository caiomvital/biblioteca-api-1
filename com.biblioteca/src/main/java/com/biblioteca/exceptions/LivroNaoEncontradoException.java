package com.biblioteca.exceptions;

public class LivroNaoEncontradoException extends RuntimeException {

	
	private static final long serialVersionUID = 1L;

	public LivroNaoEncontradoException(Long id) {
		super("Livro não encontrado com o ID " + id + ".");
	}
	
	public LivroNaoEncontradoException(String nome) {
		super("Livro não encontrado com o nome " + nome + ".");
	}
}
