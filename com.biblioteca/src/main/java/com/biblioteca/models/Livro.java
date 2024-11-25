package com.biblioteca.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "livros")
public class Livro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, length=20)
	@NotBlank(message = "O campo TÍTULO é obrigatório")
	@Size(min = 2, max = 20, message = "O campo TÍTULO deve conter entre 2 e 20 caracteres")
	private String titulo;
	@Column(nullable = false)
	private String autor;
	@ManyToOne
	@JoinColumn(name = "categoria_id", nullable = false)
	@JsonBackReference
	private Categoria categoria;
	
	public Livro() {}
	
	public Livro(Long id, String titulo, String autor, Categoria categoria) {
		
		this.id = id;
		this.titulo = titulo;
		this.autor = autor;
		this.categoria = categoria;
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

	public Categoria getCategoria() {
		return categoria;
	}
	

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	
	
	

}
