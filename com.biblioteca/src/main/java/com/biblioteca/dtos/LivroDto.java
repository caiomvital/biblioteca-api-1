package com.biblioteca.dtos;

import com.fasterxml.jackson.annotation.JsonGetter;

public class LivroDto {
	
	private Long id;
	private String titulo;
	private String autor;
	private CategoriaDto categoria;

public LivroDto() {}
	
	public LivroDto(Long id, String titulo, String autor, CategoriaDto categoriaDto) {
		
		this.id = id;
		this.titulo = titulo;
		this.autor = autor;
		this.categoria = categoriaDto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}


	public CategoriaDto getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaDto categoriaDto) {
		this.categoria = categoriaDto;
	}
	
	@JsonGetter("categoria") // Isso define como o campo será serializado no JSON
    public String getNomeCategoria() {
        return categoria.getNome();
    }

}
