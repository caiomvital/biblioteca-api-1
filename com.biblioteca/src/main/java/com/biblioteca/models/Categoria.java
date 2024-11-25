package com.biblioteca.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "categorias")
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, length = 15)
	@NotBlank(message = "O campo NOME é obrigatório")
	@Size(min=3,max=15, message = "O campo NOME deve ter entre 3 e 15 caracteres")
	private String nome;
	@OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Livro> livros;
	
	public Categoria() {}

	public Categoria(Long id, String nome, List<Livro> livros) {
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
