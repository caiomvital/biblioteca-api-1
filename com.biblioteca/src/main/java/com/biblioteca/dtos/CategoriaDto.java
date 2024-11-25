package com.biblioteca.dtos;

import java.util.ArrayList;
import java.util.List;

import com.biblioteca.models.Livro;

public class CategoriaDto {

	private Long id;
	private String nome;
	private List<Livro> livros = new ArrayList<>();

	public CategoriaDto() {}
	
	public CategoriaDto(Long id, String nome, List<Livro> livros) {
		this.id = id;
		this.nome = nome;
		this.livros = livros;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Livro> getLivros() {
		return livros;
	}

	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}
	
	
}
