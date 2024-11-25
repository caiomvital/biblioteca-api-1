package com.biblioteca.exceptions;

public class CategoriaExistenteException extends RuntimeException {
    private static final long serialVersionUID = 1L; // Identificador único para serialização

    public CategoriaExistenteException(String nome) {
        super("Uma categoria com o nome '" + nome + "' já existe.");
    }
}
