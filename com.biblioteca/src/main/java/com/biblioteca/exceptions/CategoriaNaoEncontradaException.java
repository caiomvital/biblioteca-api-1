package com.biblioteca.exceptions;

public class CategoriaNaoEncontradaException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public CategoriaNaoEncontradaException(Long id) {
        super("Categoria não encontrada com o ID " + id + ".");
    }
    
    public CategoriaNaoEncontradaException(String nome) {
    	super("Categoria não encontrada com o nome " + nome + ".");
    }
}
